<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<c:set var="title" value="Create a new Crew" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content center">
            <%-- CONTENT --%>
            <form action="controller" method="post">
                <div>
                    <label for="pilot">Pilot:</label>
                    <select id="pilot" name="id_pilot" required>
                        <c:forEach var="staff" items="${staffListForCrew}">
                            <c:if test="${staff.departamenId == 4}">
                            <option value="${staff.id}">
                                    ${staff.firstName} ${staff.lastName}
                            </option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <br/>
                <div>
                    <label for="navigator">Navigator:</label>
                    <select id="navigator" name="id_navigator" required>
                        <c:forEach var="staff" items="${staffListForCrew}">
                            <c:if test="${staff.departamenId == 1}">
                                <option value="${staff.id}">
                                        ${staff.firstName} ${staff.lastName}
                                </option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <br/>
                <div>
                    <label for="spark">Operator radio:</label>
                    <select id="spark" name="id_spark" required>
                        <c:forEach var="staff" items="${staffListForCrew}">
                            <c:if test="${staff.departamenId == 2}">
                                <option value="${staff.id}">
                                        ${staff.firstName} ${staff.lastName}
                                </option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <br/>
                <div>
                    <label for="steward">Steward/Stewardess:</label>
                    <select id="steward" name="id_steward" required>
                        <c:forEach var="staff" items="${staffListForCrew}">
                            <c:if test="${staff.departamenId == 3}">
                            <option value="${staff.id}">
                                    ${staff.firstName} ${staff.lastName}
                            </option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <br/>
                    <div>
                        <input type="hidden" name="command" value="saveCrew"/>
                        <input value="Send" type="submit"/>
                    </div>
                </div>
            </form>
            <%-- CONTENT --%>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>

</body>
</html>