<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="ru">
<head>
    <meta charset="UTF-8"/>
    <title>Оформить заказ</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/checkout-form.css" />
</head>
<body>
<div class="checkout-container">
    <h1>Оформление заказа</h1>
    <form action="${pageContext.request.contextPath}/checkout" method="post">
        <label for="address">Адрес</label>
        <textarea id="address" name="address" required rows="3"></textarea>
        <label for="paymentMethod">Способ оплаты</label>
        <select id="paymentMethod" name="paymentMethod">
            <option value="Наличными">Наличными</option>
            <option value="Картой">Картой</option>
        </select>
        <button type="submit">Подтвердить</button>
    </form>
    <a href="${pageContext.request.contextPath}/cart" class="back-link">← Назад в корзину</a>
</div>
</body>
</html>
