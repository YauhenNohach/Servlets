<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>Multiply</title></head><body>
<h1>Multiply by 2</h1>
<form action="multiply" method="post">
    <input type="text" name="number" placeholder="Enter a number" />
    <button type="submit">Calculate</button>
</form>
<c:if test="${not empty error}">
    <div style="color:red;">${error}</div>
</c:if>
<c:if test="${not empty result}">
    <p>${number} * 2 = <strong>${result}</strong></p>
</c:if>
</body></html>