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
                <button><a href="/addStaff">Add new member to Staff</a></button>
            </c:if>

            <table id="list_staff_table">
                <thead>
                <tr>
                    <td>ID</td>
                    <td>First Name</td>
                    <td>Last Name</td>
                    <td>Department</td>
                </tr>
                </thead>

                <c:forEach var="staff" items="${staffList}">
                    <tr>
                        <td>${staff.id}</td>
                        <td>${staff.firstName}</td>
                        <td>${staff.lastName}</td>
                        <td><c:if test="${staff.departamenId == 4}">PILOT</c:if>
                            <c:if test="${staff.departamenId == 1}">NAVIGATOR</c:if>
                            <c:if test="${staff.departamenId == 2}">SPARK</c:if>
                            <c:if test="${staff.departamenId == 3}">STEWARD</c:if></td>
                        <td><c:if test="${staff.crewId == 0}">FREE</c:if>
                            <c:if test="${staff.crewId != 0}">IN CREW(BUSY) - Crew №:${staff.crewId}</c:if></td>
                        <c:if test="${userRole.name == 'admin'}">
                            <td class="content center">
                                <form id="edit_staff" action="controller" method="post">
                                    <input type="hidden" name="command" value="editStaff"/>
                                    <input type="hidden" name="id_staff" value="${staff.id}"/>
                                    <input type="submit" value="Edit">
                                </form>
                            </td>
                            <td class="content center">
                                <form id="delete_staff" action="controller" method="post">
                                    <input type="hidden" name="command" value="deleteStaff"/>
                                    <input type="hidden" name="id_staff" value="${staff.id}"/>
                                    <input type="submit" value="Delete">
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
