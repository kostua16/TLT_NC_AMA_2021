<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <style>

        h1{
            color: rgb(255, 255, 255);
            text-align: center;
        }

        body{
            background: rgb(52, 56, 61);
            padding: 80px;
        }

        .hello{
            text-align: center;
        }

        div {
            text-align: center;
        }
        .sliding-button {
            text-decoration: none;
            color: #1D1B26;
            display: inline-block;
            position: relative;
            padding: 15px 30px;
            border: 1px solid;
            border-image: linear-gradient(180deg, #ff3000, #ed0200, #ff096c, #d50082);
            border-image-slice: 1;
            margin: 10px 20px;
            font-family: 'Montserrat', sans-serif;
            text-transform: uppercase;
            overflow: hidden;
            letter-spacing: 2px;
            transition: .8s cubic-bezier(.165, .84, .44, 1);
        }
        .sliding-button:before {
            content: "";
            position: absolute;
            left: 0;
            top: 0;
            height: 0;
            width: 100%;
            z-index: -1;
            color: white;
            background: linear-gradient(180deg, #ff3000, #ed0200, #ff096c, #d50082);
            transition: .8s cubic-bezier(.165, .84, .44, 1);
        }
        .sliding-button:hover {
            background: rgba(255, 255, 255, 0);
        }
        .sliding-button:hover:before {
            bottom: 0%;
            top: auto;
            height: 100%;
        }


    </style>
</head>
<body>
<h1><%= "Добро пожаловать в банк" %></h1>
<br/>
<div>
    <form action="registration.jsp">
        <button class="sliding-button">Регистрация</button>
    </form>
    <br>
    <form action="auth.jsp">
        <button class="sliding-button">Авторизация</button>
    </form>
</div>
</body>
</html>