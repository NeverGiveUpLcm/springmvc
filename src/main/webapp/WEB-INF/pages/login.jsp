<%@ taglib prefix="fmt" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 李春苗
  Date: 2020/7/7
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>
    <fmt:message key="welcome"></fmt:message>
</h1>
<form action="">
    <fmt:message key="username"/><input/><br/>
    <fmt:message key="password"/><input/><br/>
</form>
</body>
</html>
