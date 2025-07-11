package com.fbaron.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * SecurityFilterConfig is a Java Servlet Filter that is responsible for
 * securing the application's URLs. It is configured to manage access control based on a whitelist,
 * authentication, and user roles.
 */
@WebFilter("/*")
public class SecurityFilterConfig implements Filter {

    private Set<String> whitelistUrls;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        whitelistUrls = new HashSet<>(Arrays.asList(
                "/",
                "/login",
                "/login.jsp",
                "/logout",
                "/register",

                // Static resources (typically served by DefaultServlet)
                "/css/",
                "/js/",
                "/assets/"
        ));

        System.out.println("SecurityFilter initialized with whitelist: " + whitelistUrls);
    }

    /**
     * Checks if the requested path is included in the defined whitelist.
     * It supports both exact matches and prefix matches for paths ending with '/'.
     *
     * @param path The request URI path relative to the context root.
     * @return true if the path is whitelisted, false otherwise.
     */
    private boolean isWhitelisted(String path) {
        // Check for exact matches first
        if (whitelistUrls.contains(path)) {
            return true;
        }

        for (String whitelistedPath : whitelistUrls) {
            if (whitelistedPath.endsWith("/") && !"/".equals(whitelistedPath) && path.startsWith(whitelistedPath)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false); // Do not create a new session if one doesn't exist

        String path = request.getRequestURI().substring(request.getContextPath().length());
        String loginURI = request.getContextPath() + "/login";

        boolean loggedIn = (session != null && session.getAttribute("user") != null);
        String role = (loggedIn) ? (String) session.getAttribute("role") : "GUEST"; // Default role for logging

        // 1. Check if the path is whitelisted (permitAll())
        if (isWhitelisted(path)) {
            System.out.println("SecurityFilter: Access GRANTED - Whitelisted URL: " + path);
            chain.doFilter(request, response); // Allow access
            return; // Important: terminate filter chain for whitelisted paths
        }

        // 2. If not whitelisted, check if the user is authenticated (anyRequest().authenticated())
        if (loggedIn) {
            // User is logged in, now apply role-based authorization for protected pages
            System.out.println("SecurityFilter: User '" + session.getAttribute("user") + "' is logged in. Checking roles for: " + path);

            // Role-based authorization: ADMIN pages
            if (path.startsWith("/admin")) {
                if ("ADMIN".equals(role)) {
                    System.out.println("SecurityFilter: Access GRANTED - ADMIN role for " + path);
                    chain.doFilter(request, response);
                } else {
                    System.out.println("SecurityFilter: Access DENIED (403) - Insufficient role for ADMIN page: " + path + " (Role: " + role + ")");
                    response.sendError(HttpServletResponse.SC_FORBIDDEN); // 403 Forbidden
                }
                return; // Terminate after handling admin path
            }

            // Role-based authorization: USER pages
            if (path.startsWith("/user")) {
                if ("USER".equals(role)) {
                    System.out.println("SecurityFilter: Access GRANTED - USER/ADMIN role for " + path);
                    chain.doFilter(request, response);
                } else {
                    System.out.println("SecurityFilter: Access DENIED (403) - Insufficient role for USER page: " + path + " (Role: " + role + ")");
                    response.sendError(HttpServletResponse.SC_FORBIDDEN); // 403 Forbidden
                }
                return; // Terminate after handling user path
            }

            // If logged in but path is not /admin or /user, and not whitelisted,
            // it's a protected page accessible to any authenticated user.
            System.out.println("SecurityFilter: Access GRANTED - Authenticated user for " + path);
            chain.doFilter(request, response); // Allow access for any authenticated user
        } else {
            // 3. If not whitelisted and not logged in, redirect to login page
            System.out.println("SecurityFilter: Not whitelisted and not logged in. Redirecting to " + loginURI);
            response.sendRedirect(loginURI);
        }
    }

}