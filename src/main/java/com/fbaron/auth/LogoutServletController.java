package com.fbaron.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * @author Ferney Estupinan Baron
 */
@WebServlet("/logout")
public class LogoutServletController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession  session = request.getSession(false); // Don't create new session if none exists

        if (session != null) {
            session.invalidate(); // Invalidate the session
        }

        response.sendRedirect("login");
    }
}
