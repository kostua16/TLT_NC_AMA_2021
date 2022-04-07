<%--
  Created by IntelliJ IDEA.
  User: Vlad2000
  Date: 28.01.2022
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%@ page  import="com.example.bankservlet.User"%>
<%
    User userInfo = (User) session.getAttribute("userInfo");
    Integer moneyUp = Integer.parseInt(request.getParameter("money1"));
    Integer money = userInfo.getMoney();
    userInfo.setMoney(money + moneyUp);
    response.sendRedirect("/User.jsp");
%>


</body>
</html>
