<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="common/header.jsp">
    <jsp:param name="pageTitle" value="Home"/>
</jsp:include>
<h1>Welcome to JBCP Petstore!</h1>
<p>
    We have many great breeds of pet available for your perusal.
</p>
<ul>
    <li><a href="listPets.do?species=dog">Dogs</a></li>
    <li><a href="listPets.do?species=cat">Cats</a></li>
</ul>

<h2>Items</h2>
<ul>
    <c:forEach var="item" items="${item}">
        <li><a href="viewItem.do?id=${item.name}">${item.name}</a></li>
    </c:forEach>
</ul>
<jsp:include page="common/footer.jsp"/>