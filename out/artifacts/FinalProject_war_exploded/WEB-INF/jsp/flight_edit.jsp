<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<c:set var="title" value="Creating / Modifying Flight" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content center">
            <%-- CONTENT --%>
                <form action="controller" method="post">
                    <div>
                        <legend>Flight name:</legend>
                        <input type="text" name="flightName"
                               id="flightName" value="${flight.flightName }"/><br/>
                    </div><br/>
                    <div>
                        <legend>Departure:</legend>
                        <input type="text" name="departure"
                               id="departure" value="${flight.whence }"/><br/>
                    </div><br/>
                    <div>
                        <legend>Arrival:</legend>
                        <input type="text" name="arrival"
                               id="arrival" value="${flight.whereto }"/><br/>
                    </div><br/>
                    <div>
                        <legend>Date of Departure:</legend>
                        <input type="date" name="date"
                               id="date" value="${flight.date }"/><br/>
                    </div><br/>
                    <div>
                        <legend>Flight status:</legend>
                        <input type="number" name="flightStatus" min="0" max="2"
                               id="flightStatus" value="${flight.flightStatusId }"/><br/>
                    </div><br/>
                    <div>
                    <legend>Crew Number:</legend>
                    <input type="text" name="crewNumber"
                           id="crewNumber" value="${flight.crewId }"/><br/>

                    </div><br/>
                    <div>
                        <input type="hidden" name="command" value="saveFlight"/>
                        <input type="hidden" name="id_flight" value="${flight.id}"/>
                        <input value="Send" type="submit"/>
                    </div>
                </form>
                <%-- CONTENT --%>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>

</body>
</html>
