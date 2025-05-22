<html>
<body>
<h2>Login</h2>
<form method="post" action="${pageContext.request.contextPath}/login">
    <input type="email" name="email" placeholder="Email" required/><br/>
    <input type="password" name="password" placeholder="Password" required/><br/>
    <button type="submit">Sign In</button>
</form>
<%--<c:if test="${not empty error}">--%>
<%--    <div style="color:red;">${error}</div>--%>
<%--</c:if>--%>
</body>
</html>