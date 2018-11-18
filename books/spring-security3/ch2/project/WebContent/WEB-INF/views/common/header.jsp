<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
    <title>Welcome to JBCP Pets: ${param.pageTitle}</title>
    <link rel="stylesheet" type="text/css" href="/styles/style.css"/>
</head>
<body>
<div id="header">
    <div class="username">
        Welcome, <strong>
        <c:choose>
            <c:when test="${principal.username}">
                <sec:authentication property="principal.username" />
            </c:when>
            <c:otherwise>
                <sec:authentication property="name" />
            </c:otherwise>
        </c:choose>
        </strong>
    </div>

    <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/logout">Log Out</a></li>
    </ul>
    <br />
</div>
