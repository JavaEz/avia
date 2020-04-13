<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<c:set var="title" value="All Flight" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>
            <c:if test="${userRole.name == 'admin'}">
                <button><a href="/addFlight">Create a new Flight</a></button>
            </c:if>

            <table id="list_flights_table">
                <thead>
                <tr>
                    <td>ID</td>
                    <td>Name</td>
                    <td>Departure</td>
                    <td>Destination</td>
                    <td>Date</td>
                    <td>Status</td>
                    <td>№ Crew</td>
                </tr>
                </thead>

                <c:forEach var="flight" items="${flightList}">
                    <tr>
                        <td>${flight.id}</td>
                        <td>${flight.flightName}</td>
                        <td>${flight.whence}</td>
                        <td>${flight.whereto}</td>
                        <td>${flight.date}</td>
                        <td><c:if test="${flight.flightStatusId ==3}">OPENED</c:if>
                            <c:if test="${flight.flightStatusId ==1}">ARRIVED</c:if>
                            <c:if test="${flight.flightStatusId ==2}">CANCELED</c:if></td>
                        <td>${flight.crewId}</td>
                        <c:if test="${userRole.name == 'admin'}">
                            <td class="content center">
                                <form id="edit_flight" action="controller" method="post">
                                    <input type="hidden" name="command" value="editFlight"/>
                                    <input type="hidden" name="id_flight" value="${flight.id}"/>
                                    <input value="Edit" type="submit"/>
                                </form>
                            </td>
                            <td class="content center">
                                <form id="delete_flight" action="controller" method="post">
                                    <input type="hidden" name="command" value="deleteFlight"/>
                                    <input type="hidden" name="id_flight" value="${flight.id}"/>
                                    <input value="Delete" type="submit"/>
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
