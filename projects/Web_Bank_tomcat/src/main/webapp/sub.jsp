<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "bank_system.Bankomat, bank_system.Client"%>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Снятие средств со счёта</title>
</head>
<body>
<div align="center">
    <h1>Снятие средств со счёта</h1>
    <div>
        <%
        Integer subMoney = Integer.valueOf(request.getParameter("subMoney"));
        Client currentClient = (Client) session.getAttribute("client");
        Bankomat newOperation = new Bankomat(currentClient);
        String answer = newOperation.subBalance(subMoney);
        if(answer == null){ %>
        <h1>Транзакция не прошла</h1>
        <br>
        <h2>На вашем счету недостаточно средств</h2>
        <%}else{%>
            <%=answer%>
        <%}%>
        <form action="/static/enterToBank.html"> <button type="submit">Выйти</button></form>
    </div>
</div>
</body>
</html>