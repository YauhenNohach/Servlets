<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Contacts</title>
</head>
<body>
<h1>Contacts</h1>
<form action="contacts" method="post">
    <input type="hidden" name="action" value="add"/>
    <input type="text" name="name" placeholder="Name" required />
    <input type="text" name="phone" placeholder="Phone" required />
    <button type="submit">Add</button>
</form>
<table>
    <tr><th>ID</th><th>Name</th><th>Phone</th><th>Action</th></tr>
    <c:forEach var="c" items="${contacts}">
        <tr>
            <td>${c.id}</td><td>${c.name}</td><td>${c.phone}</td>
            <td>
                <form action="contacts" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete"/>
                    <input type="hidden" name="id" value="${c.id}"/>
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
