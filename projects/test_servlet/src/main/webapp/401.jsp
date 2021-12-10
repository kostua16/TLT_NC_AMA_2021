<%--
  Более новая и расширенная версия сервлетов, посзволяет писать одиночные странички,
  похожи PHP страницы.
--%>
<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en" xml:lang="en">
<% response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); %>
<c:set var="returnTo" value='${param.getOrDefault("returnTo", "index.jsp")}' />
<head>
    <%-- Обязательные метатеги --%>
    <meta charset="utf-8" content="text/html" http-equiv="Content-Type">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%-- Bootstrap CSS --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="resources/css/style.css">
    <title>Неверное имя или пароль</title>
</head>
<body>
<div class="container">
    <div class="card">
        <div class="card-body">
            <div class="form-group row">
                <span><strong>Неверное имя или пароль!</strong></span>
            </div>
            <div class="form-group row">
                <a class="col-sm-10 offset-sm-1" href='${returnTo}'>Вернуться</a><br/>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js" type="application/javascript"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous" type="application/javascript"></script>
<script src="resources/js/default.js" type="application/javascript"></script>
</body>
</html>
