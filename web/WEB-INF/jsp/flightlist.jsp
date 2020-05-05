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
                            <option value="name"><fmt:message key='jsp.by.name'/></option>
                            <option value="number"><fmt:message key='jsp.by.flight.number'/></option>
                        </select>
                    </label>
                    <label>
                        <select name="ordering">
                            <option value="asc"><fmt:message key='jsp.by.asc'/></option>
                            <option value="desc"><fmt:message key="jsp.by.desc"/></option>
                        </select>
                    </label>
                    <input value="<fmt:message key="jsp.sort"/>" type="submit"/>
                </form>
            </div>
            <div> <fmt:message key="jsp.find.flight"/>
                <form id="flight_sampling" action="controller" method="post">
                    <input type="hidden" name="command" value="selectionFlights"/>
                    <label><fmt:message key="jsp.from"/></label>
                    <input type="text" name="from" required pattern="[a-zA-Z]{3,20}" placeholder="<fmt:message key="jsp.choose.town"/>" />
                    <label><fmt:message key="jsp.to"/></label>
                    <input type="text" name="to" required pattern="[a-zA-Z]{3,20}" placeholder="<fmt:message key="jsp.choose.town"/>" />
                    <label><fmt:message key="jsp.date"/></label>
                    <input type="date" required name="date"/>
                    <button type="submit"><fmt:message key="jsp.find.flight"/></button>
                </form>
            </div>
            <%-- CONTENT --%>
            <c:if test="${userRole.name == 'admin'}">
                <button><a href="/addFlight"><fmt:message key="jsp.create.a.new.flight"/></a></button>
            </c:if>
            <table id="list_flights_table">
                <thead>
                <tr>
                    <td><fmt:message key='jsp.id'/></td>
                    <td><fmt:message key="jsp.name"/></td>
                    <td><fmt:message key="jsp.departure"/></td>
                    <td><fmt:message key="jsp.destination"/></td>
                    <td><fmt:message key="jsp.date"/></td>
                    <td><fmt:message key="jsp.flight.status"/></td>
                    <td><fmt:message key="jsp.crew.number"/></td>
                </tr>
                </thead>
                <c:if test="${flightList.size() == 0}">
                    <h1><b><fmt:message key="jsp.flight.not.found"/></b></h1>
                </c:if>

                <c:forEach var="flight" items="${flightList}">
                    <tr>
                        <td>${flight.id}</td>
                        <td>${flight.flightName}</td>
                        <td>${flight.whence}</td>
                        <td>${flight.whereto}</td>
                        <td>${flight.date}</td>
                        <td><c:if test="${flight.flightStatusId ==3}"><fmt:message key="jsp.opened"/></c:if>
                            <c:if test="${flight.flightStatusId ==1}"><fmt:message key="jsp.arrived"/></c:if>
                            <c:if test="${flight.flightStatusId ==2}"><fmt:message key="jsp.canceled"/></c:if>
                            <c:if test="${flight.flightStatusId ==4}"><fmt:message key="jsp.preparation"/></c:if>
                        </td>
                        <td><c:if test="${flight.crewId == 0}"><fmt:message key="jsp.without.team"/></c:if>
                            <c:if test="${flight.crewId != 0}">${flight.crewId}</c:if></td>
                        <c:if test="${userRole.name == 'admin'}">
                            <td class="content center">
                                <form id="edit_flight" action="controller" method="post">
                                    <input type="hidden" name="command" value="editFlight"/>
                                    <input type="hidden" name="id_flight" value="${flight.id}"/>
                                    <input value="<fmt:message key="jsp.edit"/>" type="submit"/>
                                </form>
                            </td>
                            <td class="content center">
                                <form id="delete_flight" action="controller" method="post">
                                    <input type="hidden" name="command" value="deleteFlight"/>
                                    <input type="hidden" name="id_flight" value="${flight.id}"/>
                                    <input value="<fmt:message key="jsp.delete"/>" type="submit"/>
                                </form>
                            </td>
                        </c:if>
                        <c:if test="${userRole.name == 'dispatcher'}">
                            <td class="content center">
                                <form id="edit_flight_status" action="controller" method="post">
                                    <input type="hidden" name="command" value="editFlight"/>
                                    <input type="hidden" name="id_flight" value="${flight.id}"/>
                                    <input value="<fmt:message key="jsp.change.flight.status"/>" type="submit"/>
                                </form>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
            <br/>
            <%-- CONTENT --%>
            <button><a href="/controller?command=flightList"><fmt:message key="jsp.back"/></a></button>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>

</body>
</html>
