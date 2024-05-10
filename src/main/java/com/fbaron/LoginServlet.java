package com.fbaron;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Ferney Estupinan Baron
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username.equalsIgnoreCase("fbaron") &&
        password.equalsIgnoreCase("1234")) {
            request.setAttribute("loginMessage", "Your login was successful.");
        } else {
            request.setAttribute("loginMessage", "Invalid username or password. Please try again.");
        }

        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

}
