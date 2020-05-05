<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<c:set var="title" value="Edit Request Status" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content center">
            <%-- CONTENT --%>
            <form action="controller" method="post">
                <c:if test="${userRole.name == 'admin'}">
                    <div>
                        <label> <c:if test="${request1.requestStatusId == 3}">
                            <c:set var="pendingSelected" value="selected"/>
                            <fmt:message key="jsp.now.status.is"/> <b><fmt:message key="jsp.pending"/></b>
                        </c:if>
                            <c:if test="${request1.requestStatusId == 1}">
                                <c:set var="completedSelected" value="selected"/>
                                <fmt:message key="jsp.now.status.is"/><b><fmt:message key="jsp.completed"/></b>
                            </c:if>
                            <c:if test="${request1.requestStatusId == 2}">
                                <c:set var="canceledSelected" value="selected"/>
                                <fmt:message key="jsp.now.status.is"/> <b><fmt:message key="jsp.canceled"/></b>
                            </c:if>
                        </label>
                    </div><br/>
                    <div>
                        <label for="requestStatus"><fmt:message key="jsp.change.to"/>:</label>
                        <select id="requestStatus" name="id_requestStatus" required>
                            <option ${completedSelected} value="${request1.requestStatusId = 1}">
                                <fmt:message key="jsp.completed"/>
                            </option>
                            <option ${canceledSelected} value="${request1.requestStatusId = 2}">
                                <fmt:message key="jsp.canceled"/>
                            </option>
                            <option ${pendingSelected} value="${request1.requestStatusId = 3}">
                                <fmt:message key="jsp.pending"/>
                            </option>
                        </select>
                    </div>
                    <br/>
                    <div>
                        <input type="hidden" name="command" value="saveRequestStatus"/>
                        <input type="hidden" name="id_request" value="${request1.id}"/>
                        <input value="<fmt:message key="jsp.send"/>" type="submit"/>
                    </div>
                </c:if>
            </form>
            <%-- CONTENT --%>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>

</body>
</html>
