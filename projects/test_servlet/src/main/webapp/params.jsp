<%--
  Более новая и расширенная версия сервлетов, посзволяет писать одиночные странички,
  похожи PHP страницы.
--%>
<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Random" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en" xml:lang="en">
<% pageContext.setAttribute("random", new Random().nextLong()); %>
<c:set var="user" value='${sessionScope.getOrDefault("login", "Неизвестный")}' />
<head>
    <%-- Обязательные метатеги --%>
    <meta charset="utf-8" content="text/html" http-equiv="Content-Type">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%-- Bootstrap CSS --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<%--    <link rel="stylesheet" href="resources/css/bootstrap.min.css" >--%>
<%--    <link rel="stylesheet" type="text/css" href="styles/style.css"/>--%>
    <link rel="stylesheet" href="resources/css/style.css">
    <title>Params counter</title>
</head>
<body>
<div class="container">
    <div class="card">
        <div class="card-body">
            <div class="form-group row">
                <span class="col-sm-2"><strong>Hello ${user}</strong></span><br/>
                <a class="col-sm-2 offset-sm-8" href='index.jsp'>Домой</a><br/>
            </div>
        </div>
    </div>

    <div class="card">
        <div class="card-body">
            <div class="form-group row">
                <span class="col-sm-4"><strong>Параметры запроса:</strong></span>

            </div>
            <div class="form-group row">
                <span class="col-sm-4"><strong>Параметр</strong></span>
                <span class="col-sm-7"><strong>Значение</strong></span>
            </div>
            <c:set var="parameters" value="${param.keySet()}" />
            <c:forEach items="${parameters}" var="par" >
                <div class="form-group row">
                    <span class="col-sm-4">${par}</span>
                    <span class="col-sm-7">${param[par]}</span>
                </div>
            </c:forEach>
            <div class="form-group">
                <div class="row">
                    <span class="col-sm"><strong>Параметры можно передать:</strong></span>
                </div>
                <div class="form-group">
                    <c:url value="params.jsp" var="exampleUrl">
                        <c:param name="param1" value="MyTestValue" />
                        <c:param name="param2" value='${pageContext.getAttribute("random")}' />
                    </c:url>
                    <div class="form-group">
                        <div class="row">
                            <span class="col-sm-2">Методом GET</span>
                        </div>
                        <div class="row">
                            <span class="col-sm-2 offset-sm-1">Сcылкой с параметрами</span>
                            <a class="col-sm" href="${exampleUrl}">Ссылка с параметрами</a>
                        </div>
                        <div class="row">
                            <span class="col-sm-2 offset-sm-1">Формой</span>
                            <form class="col-sm" action="params.jsp" method="get">
                                <input type="hidden" name="hiddenParameter" value="${random}" />
                                <input type="text" name="textParameter" value="текст"/>
                                <input type="password" name="passParameter" value="пароль"/>
                                <input type="submit" value="Отправить" name="submit">
                            </form>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="row">
                            <span class="col-sm-2">Методом POST</span>
                        </div>
                        <div class="row">
                            <span class="col-sm-2 offset-sm-1">Формой</span>
                            <form class="col-sm" action="params.jsp" method="post">
                                <input type="hidden" name="hiddenParameter" value="${random}" />
                                <input type="text" name="textParameter" value="текст"/>
                                <input type="password" name="passParameter" value="пароль"/>
                                <input type="submit" value="Отправить" name="submit">
                            </form>
                        </div>
                    </div>
                </div>

            </div>


        </div>
    </div>

    <div class="card">
        <div class="card-body">
            <div class="form-group row">
                <span class="col-sm-4"><strong>Технические параметры страницы:</strong></span>
            </div>
            <div class="form-group row">
                <span class="col-sm-4"><strong>Параметр</strong></span>
                <span class="col-sm-7"><strong>Значение</strong></span>
            </div>
            <div class="form-group row">
                <span class="col-sm-4">request.getRequestURL()</span>
                <span class="col-sm-7"><%=request.getRequestURL().toString()%></span>
            </div>
            <div class="form-group row">
                <span class="col-sm-4">request.getRequestURI()</span>
                <span class="col-sm-7"><%=request.getRequestURI()%></span>
            </div>
            <div class="form-group row">
                <span class="col-sm-4">request.getContextPath()</span>
                <span class="col-sm-7"><%=request.getContextPath()%></span>
            </div>
        </div>
    </div>

    <div class="card">
        <div class="card-body">
            <div class="form-group row">
                <span class="col-sm-4"><strong>Параметры страницы:</strong></span>
            </div>
            <div class="form-group row">
                <span class="col-sm-4"><strong>Параметр</strong></span>
                <span class="col-sm-7"><strong>Значение</strong></span>
            </div>
            <c:set var="parameters" value="${pageScope.keySet()}" />
            <c:forEach items="${parameters}" var="par" >
                <div class="form-group row">
                    <span class="col-sm-4">${par}</span>
                    <span class="col-sm-7">${pageScope[par]}</span>
                </div>
            </c:forEach>
        </div>
    </div>

    <div class="card">
        <div class="card-body">
            <div class="form-group row">
                <span class="col-sm-4"><strong>Параметры Сервлета(цепочки вызовов фильтров и сервлета):</strong></span>
            </div>
            <div class="form-group row">
                <span class="col-sm-4"><strong>Параметр</strong></span>
                <span class="col-sm-7"><strong>Значение</strong></span>
            </div>
            <c:set var="parameters" value="${pageContext.servletContext.attributeNames}" />
            <c:forEach items="${parameters}" var="par" >
                <div class="form-group row">
                    <span class="col-sm-4">${par}</span>
                    <span class="col-sm-7">${pageContext.servletContext.getAttribute(par)}</span>
                </div>
            </c:forEach>
        </div>
    </div>

    <div class="card">
        <div class="card-body">
            <div class="form-group row">
                <span class="col-sm-4"><strong>Параметры сессии пользователя:</strong></span>
            </div>
            <div class="form-group row">
                <span class="col-sm-4"><strong>Параметр</strong></span>
                <span class="col-sm-7"><strong>Значение</strong></span>
            </div>
            <c:set var="parameters" value="${sessionScope.keySet()}" />
            <c:forEach items="${parameters}" var="par" >
                <div class="form-group row">
                    <span class="col-sm-4">${par}</span>
                    <span class="col-sm-7">${sessionScope[par]}</span>
                </div>
            </c:forEach>
        </div>
    </div>

    <div class="card">
        <div class="card-body">
            <div class="form-group row">
                <span class="col-sm-4"><strong>Параметры всего приложения:</strong></span>
            </div>
            <div class="form-group row">
                <span class="col-sm-4"><strong>Параметр</strong></span>
                <span class="col-sm-7"><strong>Значение</strong></span>
            </div>
            <c:set var="parameters" value="${applicationScope.keySet()}" />
            <c:forEach items="${parameters}" var="par" >
                <div class="form-group row">
                    <span class="col-sm-4">${par}</span>
                    <span class="col-sm-7">${applicationScope[par]}</span>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js" type="application/javascript"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous" type="application/javascript"></script>
<script src="resources/js/default.js" type="application/javascript"></script>
</body>
</html>
