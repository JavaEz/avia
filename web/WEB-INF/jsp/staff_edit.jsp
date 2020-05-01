<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<c:set var="title" value="Creation / change of staff" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content center">
            <%-- CONTENT --%>
            <form action="controller" method="post">
                <div>
                    <label for="firstName">First name:</label>
                    <input type="text" name="firstName" id="firstName" required pattern="[a-zA-Z]{3,20}"
                           value="${staff.firstName}"/><br/>
                </div>
                <br/>
                <div>
                    <label for="lastName">First name:</label>
                    <input type="text" name="lastName" id="lastName" required pattern="[a-zA-Z]{3,20}"
                           value="${staff.lastName}"/><br/>
                </div>
                <br/>
                <div>
                    <c:if test="${staff.departamenId == 1}">
                        <c:set var="navigatorSelected" value="selected"/>
                    </c:if>
                    <c:if test="${staff.departamenId == 2}">
                        <c:set var="sparkSelected" value="selected"/>
                    </c:if>
                    <c:if test="${staff.departamenId == 3}">
                        <c:set var="stewardSelected" value="selected"/>
                    </c:if>
                    <c:if test="${staff.departamenId == 4}">
                        <c:set var="pilotSelected" value="selected"/>
                    </c:if>
                    <label for="departamenId">Departament</label>
                    <select id="departamenId" name="departamenId" required>
                        <option ${navigatorSelected} value="${staff.departamenId = 1}">
                            Navigator
                        </option>
                        <option ${sparkSelected} value="${staff.departamenId = 2}">
                            Spark
                        </option>
                        <option ${stewardSelected} value="${staff.departamenId = 3}">
                            Steward
                        </option>
                        <option ${pilotSelected} value="${staff.departamenId = 4}">
                            Pilot
                        </option>
                    </select>
<%--                    <label for="departamenId">Departament</label>--%>
<%--                    <input type="number" name="departamenId" min="1" max="4" required--%>
<%--                           id="departamenId" value="${staff.departamenId}"/><br/>--%>
                </div>
                <br/>
<%--                    <div>--%>
<%--                        <label for="crewId">crewId</label>--%>
<%--                        <input type="number" name="crewId" required--%>
<%--                               id="crewId" value="${staff.crewId}"/><br/>--%>
<%--                    </div>--%>
<%--                    <br/>--%>
                    <div>
                        <input type="hidden" name="command" value="saveStaff"/>
                        <input type="hidden" name="id_staff" value="${staff.id}"/>
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
