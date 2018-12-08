<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param name="pageTitle" value="Home"/>
</jsp:include>
<h1>Welcome to Your Account</h1>
<p>
    Please find account functions below...
</p>
<ul>
    <li><a href="changePassword">Change Password</a></li>
</ul>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>