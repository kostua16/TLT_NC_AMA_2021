<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "bank_system.Bankomat, bank_system.Client"%>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Баланс</title>
</head>
<body>
<div align="center">
    <h1>Ваш баланс</h1>
    <br>
    <div>
        <%
        Client currentClient = (Client) session.getAttribute("client");
        Bankomat newOperation = new Bankomat(currentClient);
        %>
        <h2><%=newOperation.getBalanceBank()%></h2>
        <form action="/static/enterToBank.html"> <button type="submit">Выйти</button></form>
    </div>
</div>
</body>
</html>