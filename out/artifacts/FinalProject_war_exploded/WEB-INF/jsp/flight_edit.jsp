<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<c:set var="title" value="Edit Flight" scope="page"/>
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
                    <label for="flightName">Flight name:</label>
                    <input type="text" name="flightName" required
                           id="flightName" value="${flight.flightName }"/><br/>
                </div>
                <br/>
                <div>
                    <label for="departure">Departure:</label>
                    <input type="text" name="departure" required
                           id="departure" value="${flight.whence }"/><br/>
                </div>
                <br/>
                <div>
                    <label for="arrival">Arrival:</label>
                    <input type="text" name="arrival" required
                           id="arrival" value="${flight.whereto }"/><br/>
                </div>
                <br/>
                <div>
                    <label for="date">Date of Departure:</label>
                    <input type="date" name="date" required
                           id="date" value="${flight.date }"/><br/>
                </div>
                <br/>
                <div>
                    <label>
                        <c:if test="${flight.flightStatusId == 1}">
                            Now status is <b>Arrived</b>
                        </c:if>
                        <c:if test="${flight.flightStatusId == 2}">
                            Now status is <b>Canceled</b>
                        </c:if>
                        <c:if test="${flight.flightStatusId == 3}">
                            Now status is <b>Opened</b>
                        </c:if>
                    </label>
<%--                    <label for="flightStatus">Flight status:</label>--%>
<%--                    <input type="number" name="flightStatus" min="1" max="3" required--%>
<%--                           id="flightStatus" value="${flight.flightStatusId }"/><br/>--%>
                </div><br/>
                    <div>
                        <label for="flightStatus">Change to :</label>
                        <select id="flightStatus" name="flightStatus" required>
                            <option value="${flight.flightStatusId = 1}">
                                Arrived
                            </option>
                            <option value="${flight.flightStatusId = 2}">
                                Canceled
                            </option>
                            <option value="${flight.flightStatusId = 3}">
                                Opened
                            </option>
                        </select>
                    </div><br/>
                <div>
                    <label for="crewNumber">Crew Number:</label>
                    <input type="text" name="crewNumber" required
                           id="crewNumber" value="${flight.crewId }"/><br/>

                </div>
                <br/>
                <div>
                    <input type="hidden" name="command" value="saveFlight"/>
                    <input type="hidden" name="id_flight" value="${flight.id}"/>
                    <input value="Send" type="submit"/>
                </div>
                </c:if>
                <c:if test="${userRole.name == 'dispatcher'}">
                    <div>
                        <label>
                            <c:if test="${flight.flightStatusId == 1}">
                            Now status is <b>Arrived</b>
                        </c:if>
                            <c:if test="${flight.flightStatusId == 2}">
                                Now status is <b>Canceled</b>
                            </c:if>
                            <c:if test="${flight.flightStatusId == 3}">
                                Now status is <b>Opened</b>
                            </c:if>
                        </label>
                    </div><br/>
                    <div>
                        <label for="flightStatus">Change to :</label>
                        <select id="flightStatusForDispathcer" name="flightStatus" required>
                            <option value="${flight.flightStatusId = 1}">
                                Arrived
                            </option>
                            <option value="${flight.flightStatusId = 2}">
                                Canceled
                            </option>
                            <option value="${flight.flightStatusId = 3}">
                                Opened
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
