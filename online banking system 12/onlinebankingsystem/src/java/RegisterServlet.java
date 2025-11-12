package com.bank.servlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users(username, password) VALUES(?, ?)");
            ps.setString(1, username);
            ps.setString(2, password);
            int result = ps.executeUpdate();

            if (result > 0) {
                response.sendRedirect("index.jsp?msg=registered");
            } else {
                response.sendRedirect("register.jsp?msg=error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
