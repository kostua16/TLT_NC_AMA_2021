<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Выход</title>
</head>
<body>
<div align="center">
    <h1>Вы вышли из аккаунта</h1>
    <div>
        <%
                session.removeAttribute("client");
        %>
        <form action="/static/index.html"> <button type="submit">На страницу входа</button></form>
    </div>
</div>
</body>
</html>