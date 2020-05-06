<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<head>
    <title><fmt:message key="jsp.all.requests"/></title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>
            <table id="list_staff_table">
                <thead>
                <tr>
                    <td><fmt:message key="jsp.request.id"/></td>
                    <td><fmt:message key="jsp.problem"/></td>
                    <td><fmt:message key="jsp.request.status"/></td>
                    <c:if test="${userRole.name == 'admin'}">
                        <td class="content center"><fmt:message key="jsp.action"/></td>
                    </c:if>
                </tr>
                </thead>

                <c:forEach var="request" items="${requestList}">
                    <tr>
                        <td>${request.id}</td>
                        <td><c:if test="${request.idPilot == 0}"><fmt:message key="jsp.missing.pilot"/></c:if>
                            <c:if test="${request.idNavigator == 0}"><fmt:message key="jsp.missing.navigator"/></c:if>
                            <c:if test="${request.idSpark == 0}"><fmt:message key="jsp.missing.operator.radio"/></c:if>
                            <c:if test="${request.idSteward == 0}"><fmt:message key="jsp.missing.steward"/></c:if></td>
                        <td><c:if test="${request.requestStatusId ==3}"><fmt:message key="jsp.pending"/></c:if>
                            <c:if test="${request.requestStatusId ==1}"><fmt:message key="jsp.completed"/></c:if>
                            <c:if test="${request.requestStatusId ==2}"><fmt:message key="jsp.canceled"/></c:if></td>
                        <c:if test="${userRole.name == 'admin'}">
                            <td class="content center">
                                <form id="edit_request" action="controller" method="post">
                                    <input type="hidden" name="command" value="editRequest"/>
                                    <input type="hidden" name="id_request" value="${request.id}"/>
                                    <input type="submit" value="<fmt:message key="jsp.edit.request.status"/>">
                                </form>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
            <%-- CONTENT --%>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
</html>
