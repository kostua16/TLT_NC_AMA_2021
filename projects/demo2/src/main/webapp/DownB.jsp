<%--
  Created by IntelliJ IDEA.
  User: Vlad2000
  Date: 28.01.2022
  Time: 12:56
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
    Integer moneyDown = Integer.parseInt(request.getParameter("money2"));
    Integer money = userInfo.getMoney();
    userInfo.setMoney(money - moneyDown);
    response.sendRedirect("/User.jsp");
%>
</body>
</html>
