
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp">
    <jsp:param name="pageTitle" value="Your Wishlist"/>
</jsp:include>

<h1>Your Wish List</h1>
<p>
    Here are the items you've shown an interest in...
</p>
<ul class="wishlist">
    <c:forEach items="${wishlistItems}" var="wishlistItem">
        <li><strong>${wishlistItem.title}</strong> Price: <em>${wishlistItem.displayPrice}</em>
        </li>
    </c:forEach>
</ul>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>