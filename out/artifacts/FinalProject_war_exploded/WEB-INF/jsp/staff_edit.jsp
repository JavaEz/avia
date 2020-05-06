<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<head>
    <title><fmt:message key="jsp.staff.edit"/></title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content center">
            <%-- CONTENT --%>
            <form action="controller" method="post">
                <div>
                    <label for="firstName"><fmt:message key="jsp.first.name"/>:</label>
                    <input type="text" name="firstName" id="firstName" required pattern="[a-zA-Z]{3,20}"
                           value="${staff.firstName}"/><br/>
                </div>
                <br/>
                <div>
                    <label for="lastName"><fmt:message key="jsp.last.name"/>:</label>
                    <input type="text" name="lastName" id="lastName" required pattern="[a-zA-Z]{3,20}"
                           value="${staff.lastName}"/><br/>
                </div>
                <br/>
                <div>
                    <c:if test="${staff.departamenId == 1}">
                        <c:set var="navigatorSelected" value="selected"/>
                    </c:if>
                    <c:if test="${staff.departamenId == 2}">
                        <c:set var="sparkSelected" value="selected"/>
                    </c:if>
                    <c:if test="${staff.departamenId == 3}">
                        <c:set var="stewardSelected" value="selected"/>
                    </c:if>
                    <c:if test="${staff.departamenId == 4}">
                        <c:set var="pilotSelected" value="selected"/>
                    </c:if>
                    <label for="departamenId"><fmt:message key="jsp.department"/></label>
                    <select id="departamenId" name="departamenId" required>
                        <option ${navigatorSelected} value="${staff.departamenId = 1}">
                            <fmt:message key="jsp.navigator"/>
                        </option>
                        <option ${sparkSelected} value="${staff.departamenId = 2}">
                            <fmt:message key="jsp.operator.radio"/>
                        </option>
                        <option ${stewardSelected} value="${staff.departamenId = 3}">
                            <fmt:message key="jsp.steward"/>
                        </option>
                        <option ${pilotSelected} value="${staff.departamenId = 4}">
                            <fmt:message key="jsp.pilot"/>
                        </option>
                    </select>
<%--                    <label for="departamenId">Departament</label>--%>
<%--                    <input type="number" name="departamenId" min="1" max="4" required--%>
<%--                           id="departamenId" value="${staff.departamenId}"/><br/>--%>
                </div>
                <br/>
<%--                    <div>--%>
<%--                        <label for="crewId">crewId</label>--%>
<%--                        <input type="number" name="crewId" required--%>
<%--                               id="crewId" value="${staff.crewId}"/><br/>--%>
<%--                    </div>--%>
<%--                    <br/>--%>
                    <div>
                        <input type="hidden" name="command" value="saveStaff"/>
                        <input type="hidden" name="id_staff" value="${staff.id}"/>
                        <input value="<fmt:message key="jsp.send"/>" type="submit"/>
                    </div>
            </form>
            <%-- CONTENT --%>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>

</body>
</html>
