<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head><title>Upload and Download files using Spring</title>
    <link href="./bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%--<sec:authorize access="isAuthenticated()">--%>
    <%--<p><a href="/j_spring_security_logout">Sign Out</a></p>--%>
<%--</sec:authorize>--%>
<%--<a href="/">Back</a>--%>
<%--<h1>Find by notes</h1>--%>
<%--<form:form method="get" action="files">--%>
    <%--<table width="60%" border="1" cellspacing="0">--%>
        <%--<tr>--%>
            <%--<td width="35%"><strong>Notes fragment</strong></td>--%>
            <%--<td width="65%"><input type="text" name="notes"/></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>&nbsp;</td>--%>
            <%--<td><input type="submit" name="submit" value="Find"/></td>--%>
        <%--</tr>--%>
    <%--</table>--%>
<%--</form:form>--%>
<%--<h1>Find by filename</h1>--%>
<%--<form:form method="get" action="files">--%>
    <%--<table width="60%" border="1" cellspacing="0">--%>
        <%--<tr>--%>
            <%--<td width="35%"><strong>Filename</strong></td>--%>
            <%--<td width="65%"><input type="text" name="filename"/></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>&nbsp;</td>--%>
            <%--<td><input type="submit" name="submit" value="Find"/></td>--%>
        <%--</tr>--%>
    <%--</table>--%>
<%--</form:form>--%>
<%--<div class="panel panel-default">--%>
    <%--<div class="panel-heading">Available files</div>--%>
    <%--<table class="table" width="80%" border="1" cellspacing="0" cellpadding="5">--%>
        <%--<tr>--%>
            <%--<th width="4%">No</th>--%>
            <%--<th width="30%">Filename</th>--%>
            <%--<th width="30%">Notes</th>--%>
            <%--<th width="16%">Type</th>--%>
            <%--<th width="20%">&nbsp;</th>--%>
        <%--</tr>--%>
        <%--<c:choose>--%>
            <%--<c:when test="${files != null}">--%>
                <%--<c:forEach var="file" items="${files}" varStatus="counter">--%>
                    <%--<tr>--%>
                        <%--<td>${counter.index + 1}</td>--%>
                        <%--<td>${file.filename}</td>--%>
                        <%--<td>${file.notes}</td>--%>
                        <%--<td>${file.type}</td>--%>
                        <%--<sec:authorize access="hasRole('ADMIN')">--%>
                            <%--<td>--%>
                                <%--<div align="center"><a href="download?id=${file.id}">Download</a> /--%>
                                    <%--<a href="delete?id=${file.id}">Delete</a></div>--%>
                            <%--</td>--%>
                        <%--</sec:authorize>--%>
                    <%--</tr>--%>
                <%--</c:forEach>--%>
            <%--</c:when>--%>
        <%--</c:choose>--%>
    <%--</table>--%>
<%--</div>--%>

<%--<sec:authorize access="hasRole('ADMIN')">--%>
    <%--<h2>Add New File</h2>--%>
    <%--<form:form method="post" action="upload"--%>
               <%--modelAttribute="uploadForm" enctype="multipart/form-data">--%>
        <%--<table width="60%" border="1" cellspacing="0">--%>
            <%--<tr>--%>
                <%--<td width="35%"><strong>File to upload</strong></td>--%>
                <%--<td width="65%"><input type="file" name="file"/></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td><strong>Notes</strong></td>--%>
                <%--<td><input type="text" name="notes" width="60"/></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>&nbsp;</td>--%>
                <%--<td><input type="submit" name="submit" value="Add"/></td>--%>
            <%--</tr>--%>
        <%--</table>--%>
    <%--</form:form>--%>
<%--</sec:authorize>--%>
</body>
</html>