<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<c:if test="${sessionScope.locale != null}">
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
                <label for="language"><fmt:message key="jsp.language"/></label>
                <select name="locale" id="language">
                    <c:forEach items="${applicationScope.locales}" var="locale"> 
                        <c:set var="selected" value="${locale.key == currentLocale ? 'selected' : '' }"/> 
                        <option value="${locale.value}" ${selected}><fmt:message key="${locale.value}"/></option>
                          </c:forEach>
<%--    <option value="Russian">Rus</option>--%>
<%--    <option value="English">Eng</option>--%>
                </select>

                <input type="submit" value="<fmt:message key="jsp.send"/>"/>
            </form>
            <%-- CONTENT --%>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>

</body>
</html>