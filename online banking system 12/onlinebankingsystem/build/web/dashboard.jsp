<%@ page import="javax.servlet.http.*,java.sql.*" %>
<%
    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<h2>Welcome, <%= username %>!</h2>
<p>Balance: <%= session.getAttribute("balance") %></p>
<a href="transfer.jsp">Transfer Money</a> | 
<a href="index.jsp">Logout</a>
