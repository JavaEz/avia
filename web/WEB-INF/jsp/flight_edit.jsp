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
            <form action="controller" method="post">
<%--                <input type="hidden" name="id_flight" value="${flights.id}"/>--%>
                <label
                        for="flightName"> Flight name: <input type="text" name="flightName"
                                                      id="flightName" value="${flights.flightName } ">

                </label>
                <label for="departure"> Departure: <input type="text"
                                                     name="departure" id="departure" value="${flights.whence }">

                </label>
                <label for="arrival"> Arrival: <input type="text"
                                                       name="arrival" id="arrival" value="${flights.whereto }">

                </label>
                <label for="date"> Date of Departure: <input type="text"
                                                      name="date" id="date" value="${flights.date }">

                </label>
                <label for="flightStatus"> Flight status: <input type="text"
                                                              name="flightStatus" id="flightStatus"
                                                              value="${flights.flightStatusId }">

                </label>
                <label for="crewNumber"> Crew Number: <input type="text"
                                                      name="crewNumber" id="crewNumber" value="${flights.crewId }">

                </label>
                <input type="hidden" name="command" value="saveFlight"/>
                <input type="hidden" name="id_flight" value="${flight.id}"/>
                <input value="Edit" type="submit"/>
            </form>


                <%-- CONTENT --%>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>

</body>
</html>
