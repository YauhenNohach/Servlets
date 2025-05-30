<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="ru">
<head>
    <meta charset="UTF-8"/>
    <title>Мои заказы</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/myorders.css" />
</head>
<body>
<div class="myorders-wrapper">
    <h1 class="myorders-header">Мои заказы</h1>
    <c:choose>
        <c:when test="${not empty purchaseOrders}">
            <c:forEach var="po" items="${purchaseOrders}">
                <div class="purchase-order">
                    <div class="purchase-order-title">Заказ №${po.id}</div>
                    <div class="order-meta">
                        <span>Дата: <fmt:formatDate value="${po.createdAtDate}" pattern="yyyy-MM-dd HH:mm"/></span>
                        <span>Оплата: ${po.paymentMethod}</span>
                    </div>
                    <div class="order-meta">Адрес: ${po.address}</div>
                    <ul class="order-items">
                        <c:forEach var="item" items="${po.items}">
                            <li>
                                <span>Товар ID ${item.orderId}</span>
                                <span>${item.price} ₽ × ${item.quantity}</span>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <p class="no-orders">У вас ещё нет оформленных заказов.</p>
        </c:otherwise>
    </c:choose>
    <a href="${pageContext.request.contextPath}/orders" class="back-to-products">← Назад к товарам</a>
</div>
</body>
</html>
