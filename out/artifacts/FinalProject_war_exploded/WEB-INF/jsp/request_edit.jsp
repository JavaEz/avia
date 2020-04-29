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
                            Now status is <b>Pending</b>
                        </c:if>
                            <c:if test="${request1.requestStatusId == 1}">
                                Now status is <b>Completed</b>
                            </c:if>
                            <c:if test="${request1.requestStatusId == 2}">
                                Now status is <b>Canceled</b>
                            </c:if>
                        </label>
                    </div><br/>
                    <div>
                        <label for="requestStatus">Change to:</label>
                        <select id="requestStatus" name="id_requestStatus" required>
<%--                            <option value="${request1.requestStatusId}">--%>
<%--                                <c:if test="${request1.requestStatusId == 3}">--%>
<%--                                   Now status is Pending--%>
<%--                                </c:if>--%>
<%--                                <c:if test="${request1.requestStatusId == 1}">--%>
<%--                                    Now status is Completed--%>
<%--                                </c:if>--%>
<%--                                <c:if test="${request1.requestStatusId == 2}">--%>
<%--                                    Now status is Canceled--%>
<%--                                </c:if>--%>
<%--                            </option>--%>
                            <option value="${request1.requestStatusId = 1}">
                                Completed
                            </option>
                            <option value="${request1.requestStatusId = 2}">
                                Canceled
                            </option>
                            <option value="${request1.requestStatusId = 3}">
                                Pending
                            </option>
                        </select>
<%--                        <input type="number" name="requestStatus" min="1" max="3" required--%>
<%--                               id="requestStatus" value="${request1.requestStatusId }"/><br/>--%>
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
