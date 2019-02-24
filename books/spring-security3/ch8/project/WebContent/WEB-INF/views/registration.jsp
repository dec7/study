<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="common/header.jsp">
    <jsp:param name="pageTitle" value="Register"/>
</jsp:include>

<h1>Or, Register with OpenID</h1>
<p>
    Please use the form below to register your account with OpenID.
</p>
<form action="j_spring_openid_security_check" method="post">
    <label for="openid_identifier">Login</label>:
    <input id="openid_identifier" name="openid_identifier" size="50" maxlength="100" type="text"/>
    <img src="images/openid.png" alt="OpenID"/>
    <br/>
    <input type="submit" value="Login" />
</form>

<jsp:include page="common/footer.jsp"/>