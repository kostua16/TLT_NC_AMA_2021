<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "bank_system.BankAddOperation, bank_system.Client"%>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Добавление денег на счёт</title>
</head>
<body>
<div align="center">
    <h1>Добавление денег на счёт</h1>
    <div>
        <%
        Integer plusMoney = Integer.valueOf(request.getParameter("plusMoney"));
        Client currentClient = (Client) session.getAttribute("client");
        BankAddOperation newOperation = new BankAddOperation(currentClient);
        %>
        <%=newOperation.addBalance(plusMoney)%>
        <form action="/static/enterToBank.html"> <button type="submit">Выйти</button></form>
    </div>
</div>
</body>
</html>