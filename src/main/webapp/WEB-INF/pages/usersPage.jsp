<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="templates/header.jsp" %>


<c:forEach items="${users}" var="user">
    ${user}
    <hr>
</c:forEach>


<%@include file="templates/footer.jsp" %>