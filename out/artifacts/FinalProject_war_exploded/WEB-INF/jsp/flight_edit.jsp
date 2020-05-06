<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<head>
    <title><fmt:message key="jsp.flight.edit"/></title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content center">
            <%-- CONTENT --%>
            <form action="controller" method="post">
                <c:if test="${userRole.name == 'admin'}">
                <div>
                    <label for="flightName"><fmt:message key="jsp.name"/>:</label>
                    <input type="text" name="flightName" required pattern="[a-zA-Z]{3,20}"
                           id="flightName" value="${flight.flightName }"/><br/>
                </div>
                <br/>
                <div>
                    <label for="departure"><fmt:message key="jsp.departure"/>:</label>
                    <input type="text" name="departure" required pattern="[a-zA-Z]{3,20}"
                           id="departure" value="${flight.whence }"/><br/>
                </div>
                <br/>
                <div>
                    <label for="arrival"><fmt:message key="jsp.destination"/>:</label>
                    <input type="text" name="arrival" required pattern="[a-zA-Z]{3,20}"
                           id="arrival" value="${flight.whereto }"/><br/>
                </div>
                <br/>
                <div>
                    <label for="date"><fmt:message key="jsp.date.of.departure"/></label>
                    <input type="date" name="date" required
                           id="date" value="${flight.date }"/><br/>
                </div>
                <br/>
                    <c:if test="${flight.flightStatusId == 1}">
                        <c:set var="arrivedSelected" value="selected"/>
                    </c:if>
                    <c:if test="${flight.flightStatusId == 2}">
                        <c:set var="canceledSelected" value="selected"/>
                    </c:if>
                    <c:if test="${flight.flightStatusId == 3}">
                        <c:set var="openedSelected" value="selected"/>
                    </c:if>
                    <c:if test="${flight.flightStatusId == 4}">
                        <c:set var="preparationSelected" value="selected"/>
                    </c:if>
                    <div>
                        <label for="flightStatus"><fmt:message key="jsp.change.to"/> :</label>
                        <select id="flightStatus" name="flightStatus" required>
                            <option ${arrivedSelected} value="${flight.flightStatusId = 1}">
                                <fmt:message key="jsp.arrived"/>
                            </option>
                            <option ${canceledSelected} value="${flight.flightStatusId = 2}">
                                <fmt:message key="jsp.canceled"/>
                            </option>
                            <option ${openedSelected} value="${flight.flightStatusId = 3}">
                                <fmt:message key="jsp.opened"/>
                            </option>
                            <option ${preparationSelected} value="${flight.flightStatusId = 4}">
                                <fmt:message key="jsp.preparation"/>
                            </option>
                        </select>
                    </div><br/>
                <div>
                    <label for="crewNumber"><fmt:message key="jsp.crew.number"/>:</label>
                        <select name="crewNumber" required id="crewNumber">
                            <c:if test="${flight.crewId != 0}">
                            <option>
                                    ${flight.crewId }
                            </option>
                            </c:if>
                            <c:forEach var="crew" items="${crewList}">
                                <c:if test="${crew.id != 0}">
                                <option value="${crew.id}">
                                        ${crew.id}
                                </option>
                                </c:if>
                            </c:forEach>
                                <option value="WITHOUT TEAM">
                                    <fmt:message key="jsp.without.team"/>
                                </option>
                        </select>
<%--                    <input type="number" name="crewNumber" required--%>
<%--                           id="crewNumber" value="${flight.crewId }"/><br/>--%>
                </div>
                <br/>
                <div>
                    <input type="hidden" name="command" value="saveFlight"/>
                    <input type="hidden" name="id_flight" value="${flight.id}"/>
                    <input value="<fmt:message key="jsp.send"/>" type="submit"/>
                </div>
                </c:if>
                <c:if test="${userRole.name == 'dispatcher'}">
                    <div>
                        <label>
                            <c:if test="${flight.flightStatusId == 1}">
                                <c:set var="arrivedSelected" value="selected"/>
                                <fmt:message key="jsp.now.status.is"/> <b><fmt:message key="jsp.arrived"/></b>
                        </c:if>
                            <c:if test="${flight.flightStatusId == 2}">
                                <c:set var="canceledSelected" value="selected"/>
                                <fmt:message key="jsp.now.status.is"/> <b><fmt:message key="jsp.canceled"/></b>
                            </c:if>
                            <c:if test="${flight.flightStatusId == 3}">
                                <c:set var="openedSelected" value="selected"/>
                                <fmt:message key="jsp.now.status.is"/> <b><fmt:message key="jsp.opened"/></b>
                            </c:if>
                            <c:if test="${flight.flightStatusId == 4}">
                                <c:set var="preparationSelected" value="selected"/>
                                <fmt:message key="jsp.now.status.is"/> <b><fmt:message key="jsp.preparation"/></b>
                            </c:if>
                        </label>
                    </div><br/>
                    <div>
                        <label for="flightStatus"><fmt:message key="jsp.change.to"/> :</label>
                        <select id="flightStatusForDispathcer" name="flightStatus" required>
                            <option ${arrivedSelected} value="${flight.flightStatusId = 1}">
                                <fmt:message key="jsp.arrived"/>
                            </option>
                            <option ${canceledSelected} value="${flight.flightStatusId = 2}">
                                <fmt:message key="jsp.canceled"/>
                            </option>
                            <option ${openedSelected} value="${flight.flightStatusId = 3}">
                                <fmt:message key="jsp.opened"/>
                            </option>
                            <option ${preparationSelected} value="${flight.flightStatusId = 4}">
                                <fmt:message key="jsp.preparation"/>
                            </option>
                        </select>
                    </div>
<%--                    <div>--%>
<%--                        <label for="flightStatus">Flight status:</label>--%>
<%--                        <input type="number" name="flightStatus" min="1" max="3" required--%>
<%--                               id="flightStatusForDispathcer" value="${flight.flightStatusId }"/><br/>--%>
<%--                    </div>--%>
                    <br/>
                    <div>
                        <input type="hidden" name="command" value="saveFlightStatus"/>
                        <input type="hidden" name="id_flight" value="${flight.id}"/>
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
