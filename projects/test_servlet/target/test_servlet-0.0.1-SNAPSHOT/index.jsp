<%--
  Более новая и расширенная версия сервлетов, посзволяет писать одиночные странички,
  похожи PHP страницы.
--%>
<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en" xml:lang="en">
<head>
    <%-- Обязательные метатеги --%>
    <meta charset="utf-8" content="text/html" http-equiv="Content-Type">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%-- Bootstrap CSS --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="resources/css/style.css">
    <title>Home page</title>
</head>
<body>
<div class="container">
    <div class="card">
        <form method="post" action="login">
            <input type="hidden" name="returnTo" value="index.jsp" />
            <div class="card-body">
                <c:if test='${sessionScope.containsKey("login")}'>
                    <c:set var="user" value='${sessionScope.getOrDefault("login", "Неизвестный")}' />
                    <div class="form-group row">
                        <span class="col-sm-2"><strong>Привет ${user}</strong></span><br/>
                        <div class="col-sm-1 offset-sm-9">
                            <a class="form-control btn btn-danger" href="logout">Выйти</a>
                        </div>
                    </div>
                </c:if>
                <c:if test='${!sessionScope.containsKey("login")}'>
                    <div class="form-group row">
                        <div class="col-sm-1 offset-sm-6">
                            <label for="loginLogin">Войти</label>
                        </div>
                        <div class="col-sm-2">
                            <input type="text" id="loginLogin" name="login" class="form-control"/>
                        </div>
                        <div class="col-sm-2">
                            <input type="password" id="passLogin" name="pass" class="form-control"/>
                        </div>
                        <div class="col-sm-1">
                            <input type="submit" name="submit" value="OK" class="form-control btn btn-success"/>
                        </div>
                    </div>
                </c:if>
            </div>
        </form>
    </div>

    <div class="card">
        <div class="card-body">
            <div class="form-group row">
                <span><strong>Ссылки:</strong></span>
            </div>
            <div class="form-group row">
                <a class="col-sm-10 offset-sm-1" href='params.jsp'>Пример с отправкой параметров на страницу\сервлет</a><br/>
            </div>
            <div class="form-group row">
                <a class="col-sm-10 offset-sm-1" href='count'>Счетчик посещений</a><br/>
            </div>
        </div>
    </div>

    <c:if test='${!sessionScope.containsKey("login")}'>
        <div class="card">
            <form method="post" action="register">
                <input type="hidden" name="returnTo" value="index.jsp" />
                <div class="card-body">
                    <div class="form-group row">
                        <div class="col-sm-3 offset-sm-4">
                            <label for="loginRegister">Зарегистрировать пользователя</label>
                        </div>
                        <div class="col-sm-2">
                            <input type="text" id="loginRegister" name="login" class="form-control"/>
                        </div>
                        <div class="col-sm-2">
                            <input type="password" id="passRegister" name="pass" class="form-control"/>
                        </div>
                        <div class="col-sm-1">
                            <input type="submit" name="submit" value="OK" class="form-control btn btn-primary"/>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </c:if>
</div>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js" type="application/javascript"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous" type="application/javascript"></script>
<script src="resources/js/default.js" type="application/javascript"></script>
</body>
</html>
