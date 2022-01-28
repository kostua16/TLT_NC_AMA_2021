<%--
  Created by IntelliJ IDEA.
  User: Vlad2000
  Date: 28.01.2022
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ page  import="com.example.demo2.User"%>
<%
    User userInfo = (User) session.getAttribute("userInfo");
    session.invalidate();
    response.sendRedirect("/index.jsp");
%>
</body>
</html>
