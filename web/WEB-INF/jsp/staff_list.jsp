<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<c:set var="title" value="Staff team" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>
            <c:if test="${userRole.name == 'admin'}">
                <button><a href="/addStaff"><fmt:message key="jsp.add.new.member.to.staff"/></a></button>
            </c:if>

            <table id="list_staff_table">
                <thead>
                <tr>
                    <td><fmt:message key="jsp.id"/></td>
                    <td><fmt:message key="jsp.first.name"/></td>
                    <td><fmt:message key="jsp.last.name"/></td>
                    <td><fmt:message key="jsp.department"/></td>
                </tr>
                </thead>

                <c:forEach var="staff" items="${staffList}">
                    <tr>
                        <td>${staff.id}</td>
                        <td>${staff.firstName}</td>
                        <td>${staff.lastName}</td>
                        <td><c:if test="${staff.departamenId == 4}"><fmt:message key="jsp.pilot"/></c:if>
                            <c:if test="${staff.departamenId == 1}"><fmt:message key="jsp.navigator"/></c:if>
                            <c:if test="${staff.departamenId == 2}"><fmt:message key="jsp.operator.radio"/></c:if>
                            <c:if test="${staff.departamenId == 3}"><fmt:message key="jsp.steward"/></c:if></td>
                        <td><c:if test="${staff.crewId == 0}"><fmt:message key="jsp.free"/></c:if>
                            <c:if test="${staff.crewId != 0}"><fmt:message key="jsp.in.crew.busy"/>${staff.crewId}</c:if></td>
                        <c:if test="${userRole.name == 'admin'}">
                            <td class="content center">
                                <form id="edit_staff" action="controller" method="post">
                                    <input type="hidden" name="command" value="editStaff"/>
                                    <input type="hidden" name="id_staff" value="${staff.id}"/>
                                    <input type="submit" value="<fmt:message key="jsp.edit"/>">
                                </form>
                            </td>
                            <td class="content center">
                                <form id="delete_staff" action="controller" method="post">
                                    <input type="hidden" name="command" value="deleteStaff"/>
                                    <input type="hidden" name="id_staff" value="${staff.id}"/>
                                    <input type="submit" value="<fmt:message key="jsp.delete"/>">
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
