<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<c:if test="${requestScope.locale != null}">
    <fmt:setLocale value="${param.locale}" scope="session"/>
    <fmt:setBundle basename="resources"/>
    <c:set var="currentLocale" value="${param.locale}" scope="session"/>
</c:if>
<html>
<c:set var="title" value="Settings" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<table id="main-container">

<%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content center">
            <%-- CONTENT --%>
            <form action="controller" method="post">
                <input type="hidden" name="command" value="viewSettings"/>
                <label for="language">Language</label>
                <select id="language" name="locale">
                    <c:forEach items="${applicationScope.locales}" var="locale">
                        <c:set var="selected" value="${locale.key == currentLocale ? 'selected' : '' }"/>
                        <option value="${locale.key}" ${selected}>${locale.value}</option>
                    </c:forEach>
    <option value="English"> English</option>
    <option value="Russian"> Russian</option>
                </select>
                <input type="submit" value="Save"/>
            </form>
            <%-- CONTENT --%>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>

</body>
</html>