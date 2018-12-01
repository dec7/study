<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param name="pageTitle" value="Home"/>
</jsp:include>
<h1>Change password</h1>
<form method="post">
    <label form="password">New password</label>:
    <input id="password" name="password" size="20" maxlength="50" type="password" />
    <br/>
    <input type="submit" value="Change password"/>
</form>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>