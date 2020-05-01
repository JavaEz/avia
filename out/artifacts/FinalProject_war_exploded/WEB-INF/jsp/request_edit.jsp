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
                            Now status is <b>Pending</b>
                        </c:if>
                            <c:if test="${request1.requestStatusId == 1}">
                                <c:set var="completedSelected" value="selected"/>
                                Now status is <b>Completed</b>
                            </c:if>
                            <c:if test="${request1.requestStatusId == 2}">
                                <c:set var="canceledSelected" value="selected"/>
                                Now status is <b>Canceled</b>
                            </c:if>
                        </label>
                    </div><br/>
                    <div>
                        <label for="requestStatus">Change to:</label>
                        <select id="requestStatus" name="id_requestStatus" required>
                            <option ${completedSelected} value="${request1.requestStatusId = 1}">
                                Completed
                            </option>
                            <option ${canceledSelected} value="${request1.requestStatusId = 2}">
                                Canceled
                            </option>
                            <option ${pendingSelected} value="${request1.requestStatusId = 3}">
                                Pending
                            </option>
                        </select>
                    </div>
                    <br/>
                    <div>
                        <input type="hidden" name="command" value="saveRequestStatus"/>
                        <input type="hidden" name="id_request" value="${request1.id}"/>
                        <input value="Send" type="submit"/>
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
