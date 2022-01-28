<%@ page import="com.example.demo2.User" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Моя страница</title>
</head>
<body>
<%
    User userInfo = (User)session.getAttribute("userInfo") ;
    String userName = request.getParameter("user");

    Integer count = (Integer) session.getAttribute("count");

    if (userInfo == null){
        userInfo = new User();

        userInfo.setName(userName);
        userInfo.setMoney(0);
        session.setAttribute("userInfo", userInfo);
    }


    Integer money = userInfo.getMoney();

    if (count == null) {
        session.setAttribute("count", 1);
        count = 1;
    }

    else {
        session.setAttribute("count", count + 1);
    }
    PrintWriter pw = response.getWriter();

%>


<h1><%="Здравствуй " + userInfo.getName()%></h1>
<br>
<%="Вы были на странице " + count%>
<br>
<%= "Ваш баланс:" + money%>
<br>
<form action="UpB.jsp" method="post">
    Пополнить баланс: <input type="number" name="money1">
    <button>Пополнить</button>

</form>
<br>
<form action="DownB.jsp" method="post">
    Снять: <input type="number" name="money2" >
    <button>Снять</button>
</form>
<br>
<form action="stop.jsp" method="post">
    <button>Выйти из аккаунта</button>
</form>
<%

%>


</body>
</html>
