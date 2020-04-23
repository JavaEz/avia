<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<c:set var="title" value="All Flight" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<c:set var="searchForForm" value="active" scope="page"/>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <div>
                <form id="sorting_flights" action="controller" method="post">
                    <input type="hidden" name="command" value="flightList"/>
                    <label>
                        <select name="sorting">
                            <option value="name">By name</option>
                            <option value="number">By flight number</option>
                        </select>
                    </label>
                    <label>
                        <select name="ordering">
                            <option value="asc">By ASC</option>
                            <option value="desc">By DESC</option>
                        </select>
                    </label>
                    <input value="Sort" type="submit"/>
                </form>
            </div>
            <div> НАЙТИ РЕЙС
                <form id="flight_sampling" action="controller" method="post">
                    <input type="hidden" name="command" value="selectionFlights"/>
                    <label>From</label>
                    <input type="text" name="from" required placeholder="Choose Town" />
                    <label>To</label>
                    <input type="text" name="to" required placeholder="Choose Town" />
                    <label>Date</label>
                    <input type="date" required name="date"/>
                    <button type="submit">Find flight</button>
                </form>
            </div>
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
                        <c:if test="${userRole.name == 'dispatcher'}">
                            <td class="content center">
                                <form id="edit_flight_status" action="controller" method="post">
                                    <input type="hidden" name="command" value="editFlight"/>
                                    <input type="hidden" name="id_flight" value="${flight.id}"/>
                                    <input value="Change flight status" type="submit"/>
                                </form>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
            <br/>
            <%-- CONTENT --%>
            <button onclick="goBack()">Go Back</button>

            <script>
                function goBack() {
                    window.history.back();
                }
            </script>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>

</body>
</html>
