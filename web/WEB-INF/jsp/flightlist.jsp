<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<c:set var="title" value="All Flight" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
    <table id="main-container">

        <%@ include file="/WEB-INF/jspf/header.jspf" %>

        <tr>
            <td class="content">
                <%-- CONTENT --%>
                    <form id="make_order" action="controller">
                        <input type="hidden" name="command" value="makeOrder"/>
                        <input value="Make an order" type="submit"/>
                    </form>

                        <table id="list_menu_table">
                            <thead>
                            <tr>
                                <td>ID</td>
                                <td>NAME</td>
                                <td>Departure</td>
                                <td>Destination</td>
                                <td>Date</td>
                                <td>Status</td>
                                <td>Number of Crew</td>
                            </tr>
                            </thead>

                            <c:forEach var="flight" items="${flightList}">
                                <tr>
                                    <td>${flight.id}</td>
                                    <td>${flight.flightName}</td>
                                    <td>${flight.whence}</td>
                                    <td>${flight.whereto}</td>
                                    <td>${flight.date}</td>
                                    <td><c:if test="${flight.flightStatusId ==0}">OPENED</c:if>
                                        <c:if test="${flight.flightStatusId ==1}">CLOSED</c:if>
                                        <c:if test="${flight.flightStatusId ==2}">CANCELED</c:if></td>
                                    <td>${flight.crewId}</td>
                                    <td class="content center">
                                        <form id="action" action="controller">
                                            <input type="hidden" name="command" value="edit"/>
                                            <input value="Edit" type="submit"/>
                                        </form>
                                      </td>
                                    <td class="content center">
                                        <form id="actionTwo" action="controller">
                                            <input type="hidden" name="command" value="delete"/>
                                            <input value="Delete" type="submit"/>
                                        </form>
                                    </td>

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
