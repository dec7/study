<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="common/header.jsp">
    <jsp:param name="pageTitle" value="Login"/>
</jsp:include>

<h1>Please Log In to Your Account</h1>
<p>
    Please use the form below to log in to your account.
</p>
<form action="j_spring_security_check" method="post">
    <label for="j_username">Login</label>:
    <input id="j_username" name="j_username" size="20" maxlength="50" type="text"/>
    <br />

    <label for="j_password">Password</label>:
    <input id="j_password" name="j_password" size="20" maxlength="50" type="password"/>
    <br />

    <input id="_remember_me" name="_remember_me"
           type="checkbox" value="true"/>
    <label for="_remember_me">Remember Me?</label>
    <br />

    <input type="submit" value="Login"/>
</form>

<h1>Or, Log Into Your Account with OpenID</h1>
<p>
    Please use the form below to log into your account with OpenID.
</p>
<form action="j_spring_openid_security_check" method="post">
    <label for="openid_identifier">Login</label>:
    <input id="openid_identifier" name="openid_identifier" size="20" maxlength="100" type="text"/>
    <img src="images/openid.png" alt="OpenID" />
    <br/>
    <input type="submit" value="Login" />
</form>

<jsp:include page="common/footer.jsp"/>