<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html> <html> <head> <meta charset="UTF-8"> <title>Корзина</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/cart.css">
</head> <body> <h1>Корзина</h1> <c:choose> <c:when test="${not empty cart}">
    <table border="1"> <tr> <th>Название</th> <th>Изображение</th>
        <th>Цена</th> <th>Удалить</th>
    </tr> <c:forEach var="o" items="${cart}" varStatus="status"> <tr>
        <td>${o.name}</td> <td><img src="${o.imageUrl}" alt="${o.name}" width="100"/></td>
        <td><fmt:formatNumber value="${o.price}" type="currency"/></td>
        <td> <form action="${pageContext.request.contextPath}/cart" method="post">
            <input type="hidden" name="action" value="remove"/>
            <input type="hidden" name="index" value="${status.index}"/>
            <button type="submit">Удалить</button> </form> </td> </tr> </c:forEach> </table>
    <form action="${pageContext.request.contextPath}/cart" method="post"> <input type="hidden" name="action" value="clear"/>
        <button type="submit">Очистить корзину</button> </form>
    <form action="${pageContext.request.contextPath}/checkout" method="get">
        <button type="submit">Оформить заказ</button>
    </form>
</c:when> <c:otherwise>

    <p>Корзина пуста</p> </c:otherwise> </c:choose> <p><a href="${pageContext.request.contextPath}/orders">← Назад к товарам</a></p>
</body> </html>

