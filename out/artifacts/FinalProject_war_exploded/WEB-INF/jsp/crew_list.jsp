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
            <c:if test="${userRole.name == 'dispatcher'}">
                <form id="edit_staff" action="controller" method="post">
                    <input type="hidden" name="command" value="addCrew"/>
                    <input type="submit" value="Create a new Crew">
                </form>
            </c:if>

            <table id="list_staff_table">
                <thead>
                <tr>
                    <td>Crew ID</td>
                    <td>Crew Status</td>
                    <td>Pilots</td>
                    <td>Navigators</td>
                    <td>Operators radio</td>
                    <td>Steward/Stewardess</td>
                    <c:if test="${userRole.name == 'dispatcher'}">
                        <td class="content center">Action</td>
                        <td class="content center">Action2</td>
                    </c:if>
                </tr>
                </thead>

                <c:forEach var="crew" items="${crewList}">
                    <tr>
                        <td>${crew.id}</td>
                        <td><c:if test="${crew.id != 0}">
                            <c:if test="${crew.crewStatusId == 2}">Not Ready</c:if>
                            <c:if test="${crew.crewStatusId == 1}">Ready</c:if>
                        </c:if>
                            <c:if test="${crew.id == 0}">Free person</c:if></td>
                        <td><c:forEach var="staff" items="${staffList}">
                            <c:if test="${staff.crewId == crew.id}">
                                <c:if test="${staff.departamenId == 4}">
                                    <p> ${staff.firstName}
                                            ${staff.lastName}</p></c:if>
                            </c:if>
                        </c:forEach></td>
                        <td><c:forEach var="staff" items="${staffList}">
                            <c:if test="${staff.crewId == crew.id}">
                                <c:if test="${staff.departamenId == 1}">
                                    <p> ${staff.firstName}
                                            ${staff.lastName}</p></c:if>
                            </c:if>
                        </c:forEach></td>
                        <td><c:forEach var="staff" items="${staffList}">
                            <c:if test="${staff.crewId == crew.id}">
                                <c:if test="${staff.departamenId == 2}">
                                    <p> ${staff.firstName}
                                            ${staff.lastName}</p></c:if>
                            </c:if>
                        </c:forEach></td>
                        <td><c:forEach var="staff" items="${staffList}">
                            <c:if test="${staff.crewId == crew.id}">
                                <c:if test="${staff.departamenId == 3}">
                                    <p> ${staff.firstName}
                                            ${staff.lastName}</p>
                                </c:if>
                            </c:if>
                        </c:forEach></td>
                        <c:if test="${userRole.name == 'dispatcher'}">
                            <c:if test="${crew.id != 0}">
                                <td class="content center">
                                    <form id="edit_crew" action="controller" method="post">
                                        <input type="hidden" name="command" value="editCrew"/>
                                        <input type="hidden" name="id_crew" value="${crew.id}"/>
                                        <input type="submit" value="Edit">
                                    </form>
                                </td>
                                <td class="content center">
                                    <form id="delete_crew" action="controller" method="post">
                                        <input type="hidden" name="command" value="deleteCrew"/>
                                        <input type="hidden" name="id_crew" value="${crew.id}"/>
                                        <input type="submit" value="Delete">
                                    </form>
                                </td>
                            </c:if>
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
