<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<head>
    <title><fmt:message key="jsp.crew.edit"/></title>
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
                    <label for="pilot"><fmt:message key="jsp.pilot"/>:</label>
                    <select id="pilot" name="id_pilot" required>
                        <c:forEach var="staff" items="${staffListForEdit}">
                        <c:if test="${staff.departamenId == 4}">
                        <option value="${staff.id}">
                            ${staff.firstName} ${staff.lastName}
                        </option>
                        </c:if>
                        </c:forEach>
                        <c:forEach var="staff" items="${freeStaffListForCrew}">
                            <c:if test="${staff.departamenId == 4}">
                                <option value="${staff.id}">
                                        ${staff.firstName} ${staff.lastName}
                                </option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <br/>
                <div>
                    <label for="navigator"><fmt:message key="jsp.navigator"/>:</label>
                    <select id="navigator" name="id_navigator" required>
                        <c:forEach var="staff" items="${staffListForEdit}">
                            <c:if test="${staff.departamenId == 1}">
                                <option value="${staff.id}">
                                        ${staff.firstName} ${staff.lastName}
                                </option>
                            </c:if>
                        </c:forEach>
                        <c:forEach var="staff" items="${freeStaffListForCrew}">
                            <c:if test="${staff.departamenId == 1}">
                                <option value="${staff.id}">
                                        ${staff.firstName} ${staff.lastName}
                                </option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <br/>
                <div>
                    <label for="spark"><fmt:message key="jsp.operator.radio"/>:</label>
                    <select id="spark" name="id_spark" required>
                        <c:forEach var="staff" items="${staffListForEdit}">
                            <c:if test="${staff.departamenId == 2}">
                                <option value="${staff.id}">
                                        ${staff.firstName} ${staff.lastName}
                                </option>
                            </c:if>
                        </c:forEach>
                        <c:forEach var="staff" items="${freeStaffListForCrew}">
                            <c:if test="${staff.departamenId == 2}">
                                <option value="${staff.id}">
                                        ${staff.firstName} ${staff.lastName}
                                </option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <br/>
                <div>
                    <label for="steward"><fmt:message key="jsp.steward"/>:</label>
                    <select id="steward" name="id_steward" required>
                        <c:forEach var="staff" items="${staffListForEdit}">
                            <c:if test="${staff.departamenId == 3}">
                                <option value="${staff.id}">
                                        ${staff.firstName} ${staff.lastName}
                                </option>
                            </c:if>
                        </c:forEach>
                        <c:forEach var="staff" items="${freeStaffListForCrew}">
                            <c:if test="${staff.departamenId == 3}">
                                <option value="${staff.id}">
                                        ${staff.firstName} ${staff.lastName}
                                </option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <br/>
                <div>
                    <input type="hidden" name="command" value="saveCrew"/>
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