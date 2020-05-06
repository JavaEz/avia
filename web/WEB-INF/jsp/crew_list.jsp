<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<head>
    <title><fmt:message key="jsp.all.crew"/></title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>
            <c:if test="${userRole.name == 'dispatcher'}">
                <form id="edit_staff" action="controller" method="post">
                    <input type="hidden" name="command" value="addCrew"/>
                    <input type="submit" value="<fmt:message key="jsp.create.a.new.crew"/>">
                </form>
            </c:if>

            <table id="list_staff_table">
                <thead>
                <tr>
                    <td><fmt:message key="jsp.crew.number"/></td>
                    <td><fmt:message key="jsp.crew.status"/></td>
                    <td><fmt:message key="jsp.pilots"/></td>
                    <td><fmt:message key="jsp.navigators"/></td>
                    <td><fmt:message key="jsp.operators.radio"/></td>
                    <td><fmt:message key="jsp.stewards"/></td>
                    <c:if test="${userRole.name == 'dispatcher'}">
                        <td class="content center"><fmt:message key="jsp.action"/></td>
                        <td class="content center"><fmt:message key="jsp.action"/>2</td>
                    </c:if>
                </tr>
                </thead>

                <c:forEach var="crew" items="${crewList}">
                    <tr>
                        <td>${crew.id}</td>
                        <td><c:if test="${crew.id != 0}">
                            <c:if test="${crew.crewStatusId == 3}"><fmt:message key="jsp.free.for.flight"/></c:if>
                            <c:if test="${crew.crewStatusId == 2}"><fmt:message key="jsp.not.ready"/></c:if>
                            <c:if test="${crew.crewStatusId == 1}">
                                <c:forEach var="flight" items="${flightList}">
                                    <c:if test="${flight.crewId == crew.id}">
                                        <fmt:message key="jsp.ready.in.flight"/> ${flight.flightName}
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </c:if>
                            <c:if test="${crew.id == 0}"><fmt:message key="jsp.free.person"/></c:if></td>
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
                                        <input type="submit" value="<fmt:message key="jsp.edit"/>">
                                    </form>
                                </td>
                                <td class="content center">
                                    <form id="delete_crew" action="controller" method="post">
                                        <input type="hidden" name="command" value="deleteCrew"/>
                                        <input type="hidden" name="id_crew" value="${crew.id}"/>
                                        <input type="submit" value="<fmt:message key="jsp.delete"/>">
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
