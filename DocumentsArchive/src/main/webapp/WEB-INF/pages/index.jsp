<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!doctype>
<html>
<head>
    <title>Домашня сторінка</title>
    <link href="./bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%--<div>--%>
    <%--<h1>Домашня стрінка</h1>--%>
    <%--<sec:authorize access="isAnonymous()">--%>
        <%--<p>--%>
            <%--<a href="/spring_security_login">Ввійти</a>--%>
        <%--</p>--%>
    <%--</sec:authorize>--%>

    <%--<sec:authorize access="isAuthenticated()">--%>
        <%--<p>Привіт! ${userDetails.username}! <a href="/j_spring_security_logout">Sign Out</a></p>--%>
        <%--<a href="/files">Files</a>--%>
    <%--</sec:authorize>--%>

    <%--<sec:authorize access="hasRole('ADMIN')">--%>
        <%--<p>--%>
            <%--<a href="/admin">Admin page</a>--%>
        <%--</p>--%>
    <%--</sec:authorize>--%>
<%--</div>--%>
</body>
</html>