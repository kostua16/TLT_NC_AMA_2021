<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ page  import="com.example.bankservlet.User"%>
<%
    User userInfo = (User) session.getAttribute("userInfo");
    Integer moneyDown = Integer.parseInt(request.getParameter("money2"));
    Integer money = userInfo.getMoney();
    userInfo.setMoney(money - moneyDown);
    response.sendRedirect("/User.jsp");
%>
</body>
</html>

