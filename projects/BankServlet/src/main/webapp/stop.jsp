<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<%@ page  import="com.example.bankservlet.User"%>
<%
  User userInfo = (User) session.getAttribute("userInfo");
  session.invalidate();
  response.sendRedirect("/index.jsp");
%>
</body>
</html>
