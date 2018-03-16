<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="templates/header.jsp" %>


<form action="/joinUP" method="post">
    <select name="userID" id="">
        <c:forEach items="${users}" var="user">
            <option value="${user.id}">${user.username}</option>
        </c:forEach>
    </select>
    <select name="productID" id="">
        <c:forEach items="${products}" var="product">
            <option value="${product.id}">${product.title}</option>
        </c:forEach>
    </select>

    <input type="submit" name="" placeholder="">
    <input type="hidden"
    	name="${_csrf.parameterName}"
    	value="${_csrf.token}"/>
</form>


<%@include file="templates/footer.jsp" %>