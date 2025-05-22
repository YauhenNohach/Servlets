<%@ page contentType="text/html; charset=UTF-8" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<html>
<body>
<h2>Register</h2>
<form method="post" action="${pageContext.request.contextPath}/register">
    <input type="email" name="email" placeholder="Email" required/><br/>
    <input type="password" name="password" placeholder="Password" required/><br/>
    <button type="submit">Sign Up</button>
</form>
<%--<c:if test="${not empty error}">--%>
<%--    <div style="color:red;">${error}</div>--%>
<%--</c:if>--%>
</body>
</html>