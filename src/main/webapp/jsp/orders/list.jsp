<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8" />
    <title>Заказы</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css" />
</head>
<body>

<h1>Добавить заказ</h1>
<form action="${pageContext.request.contextPath}/orders" method="post">
    <input type="hidden" name="action" value="add" />
    <input type="text" name="name" placeholder="Название товара" required />
    <input type="text" name="image" placeholder="Ссылка на изображение" required />
    <input type="number" name="price" placeholder="Цена" step="0.01" required />
    <button type="submit">Добавить</button>
</form>

<h2>Список товаров</h2>
<div class="cards-container">
    <c:forEach var="o" items="${orders}">
        <div class="card">
            <img src="${o.imageUrl}" alt="${o.name}" />
            <h3>${o.name}</h3>
            <div class="price">${o.price} ₽</div>
            <form action="${pageContext.request.contextPath}/cart" method="post">
                <input type="hidden" name="action" value="add" />
                <input type="hidden" name="orderId" value="${o.id}" />
                <button type="submit">Добавить в корзину</button>
            </form>
        </div>
    </c:forEach>
</div>

<a href="${pageContext.request.contextPath}/cart" class="cart-link">Перейти в корзину</a>

</body>
</html>
