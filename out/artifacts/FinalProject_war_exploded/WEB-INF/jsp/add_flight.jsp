<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<c:set var="title" value="Create a new Flight" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content center">
            <%-- CONTENT --%>
            <form action="controller" method="post">
                <div>
                    <laber for="flightName">Flight name:</laber>
                    <input type="text" name="flightName" required pattern="[a-zA-Z]{3,20}"
                           id="flightName" /><br/>
                </div>
                <br/>
                <div>
                    <label for="departure">Departure:</label>
                    <input type="text" name="departure" required pattern="[a-zA-Z]{3,20}"
                           id="departure"/><br/>
                </div>
                <br/>
                <div>
                    <label for="arrival">Arrival:</label>
                    <input type="text" name="arrival" required pattern="[a-zA-Z]{3,20}"
                           id="arrival"/><br/>
                </div>
                <br/>
                <div>
                    <label for="date">Date of Departure:</label>
                    <input type="date" name="date" required
                           id="date"/><br/>
                </div>
                <br/>
<%--                <div>--%>
<%--                    <label for="flightStatus">Flight status:</label>--%>
<%--                    <input type="number" name="flightStatus" min="1" max="3" required--%>
<%--                           id="flightStatus"/><br/>--%>
<%--                </div>--%>
<%--                <br/>--%>
<%--                <div>--%>
<%--                    <label for="crewNumber">Crew Number:</label>--%>
<%--                    <input type="text" name="crewNumber" required--%>
<%--                           id="crewNumber"/><br/>--%>

<%--                </div>--%>
                <br/>
                <div>
                    <input type="hidden" name="command" value="saveFlight"/>
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
