package com.fbaron;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

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
        String loginMessage = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/fbank", "root", "rootpassword");

            String query = "SELECT * FROM user WHERE username = ? AND password = ?";

            PreparedStatement preparedStatement =  connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                loginMessage = "Your login was successful";
            } else {
                loginMessage = "Invalid username or password, please try again.";
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("LoginServlet doPost error: " + e.getMessage());
            loginMessage = "An error occurred while processing the login request";
        } finally {
            request.setAttribute("loginMessage", loginMessage);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }

}
