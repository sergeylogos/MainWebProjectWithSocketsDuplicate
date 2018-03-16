<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="templates/header.jsp" %>
<h1>Index page</h1>
<p>principal: ${principal}</p>
<security:authorize access="isAuthenticated()">you are authenticated <a href="/logout">logout</a></security:authorize>
<h3>save user form</h3>
<form action="save" method="post">
    <input type="text" name="name">
    <input type="text" name="password">
    <input type="submit">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

</form>
<h3>product save</h3>
<form action="/saveProduct" method="post">

    <input type="" name="title" placeholder="title">
    <input type="number" name="capacity" placeholder="capacity">
    <input type="submit" name="" placeholder="">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form>

<hr>
<%--<form action="pageNumber">--%>
<%--<input type="number" name="pageNumber">--%>
<%--<input type="submit">--%>
<%--</form>--%>
<c:forEach items="${all}" var="product">
    <p>${product}</p>
</c:forEach>

<hr>
<a href="chat"> chat page</a>

<ul>
    <li><a href="/usersPage">/usersPage</a></li>
    <li><a href="/productsPage">/productsPage</a></li>
</ul>

<hr>
<a href="/join">join</a>
<%@include file="templates/footer.jsp" %>