<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<c:set var="title" value="All requests" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>
            <table id="list_staff_table">
                <thead>
                <tr>
                    <td>Request ID</td>
                    <td>Problem</td>
                    <td>Request status</td>
                    <c:if test="${userRole.name == 'admin'}">
                        <td class="content center">Action</td>
                        <td class="content center">Action2</td>
                    </c:if>
                </tr>
                </thead>

                <c:forEach var="request" items="${requestList}">
                    <tr>
                        <td>${request.id}</td>
                        <td><c:if test="${request.idPilot == 0}">Missing Pilot</c:if>
                            <c:if test="${request.idNavigator == 0}">Missing Navigator</c:if>
                            <c:if test="${request.idSpark == 0}">Missing Spark</c:if>
                            <c:if test="${request.idSteward == 0}"> Missing Steward</c:if></td>
                        <td><c:if test="${request.requestStatusId ==3}">PENDING</c:if>
                            <c:if test="${request.requestStatusId ==1}">COMPLETED</c:if>
                            <c:if test="${request.requestStatusId ==2}">CANCELED</c:if></td>
                        <c:if test="${userRole.name == 'admin'}">
                            <td class="content center">
                                <form id="edit_request" action="controller" method="post">
                                    <input type="hidden" name="command" value="editRequest"/>
                                    <input type="hidden" name="id_request" value="${request.id}"/>
                                    <input type="submit" value="Edit request status">
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
