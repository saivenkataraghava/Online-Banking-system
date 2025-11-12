package com.bank.servlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class TransferServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fromUser = (String) request.getSession().getAttribute("username");
        String toUser = request.getParameter("toUser");
        double amount = Double.parseDouble(request.getParameter("amount"));

        try {
            Connection conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement check = conn.prepareStatement("SELECT balance FROM users WHERE username=?");
            check.setString(1, fromUser);
            ResultSet rs = check.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("balance");
                if (balance >= amount) {
                    PreparedStatement debit = conn.prepareStatement("UPDATE users SET balance = balance - ? WHERE username=?");
                    debit.setDouble(1, amount);
                    debit.setString(2, fromUser);
                    debit.executeUpdate();

                    PreparedStatement credit = conn.prepareStatement("UPDATE users SET balance = balance + ? WHERE username=?");
                    credit.setDouble(1, amount);
                    credit.setString(2, toUser);
                    int updated = credit.executeUpdate();

                    if (updated > 0) {
                        conn.commit();
                        response.sendRedirect("dashboard.jsp?msg=success");
                    } else {
                        conn.rollback();
                        response.sendRedirect("dashboard.jsp?msg=user_not_found");
                    }
                } else {
                    response.sendRedirect("dashboard.jsp?msg=insufficient");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

