<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param name="pageTitle" value="Home"/>
</jsp:include>
<h1>Access Denied</h1>
<p>
    Access to the specified resource has been denied for
    the following reason: <strong>${errorDetails}</strong>
</p>
<em>Error Details (for support Purposes only:</em><br/>
<blockquote>
    <pre>${errorTrace}</pre>
</blockquote>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>