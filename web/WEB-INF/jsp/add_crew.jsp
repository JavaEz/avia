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
                    <select id="pilot" name="id_pilot">
                        <c:set var="i" value="0"/>
                        <c:forEach var="staff" items="${staffListForCrew}">
                            <c:if test="${staff.departamenId == 4}">
                                <c:set var="i" value="${i+1}"/>
                                <option value="${staff.id}">
                                        ${staff.firstName} ${staff.lastName}
                                </option>
                            </c:if>
                        </c:forEach>
                    </select>
                    <c:if test="${i==0}">
                        <c:set var="missed" value="Hooray"/>
                    </c:if>
                </div>
                <br/>
                <div>
                    <label for="navigator">Navigator:</label>
                    <select id="navigator" name="id_navigator">
                        <c:set var="k" value="0"/>
                        <c:forEach var="staff" items="${staffListForCrew}">
                            <c:if test="${staff.departamenId == 1}">
                                <c:set var="k" value="${k+1}"/>
                                <option value="${staff.id}">
                                        ${staff.firstName} ${staff.lastName}
                                </option>
                            </c:if>
                        </c:forEach>
                    </select>
                    <c:if test="${k==0}">
                        <c:set var="missed" value="Hooray"/>
                    </c:if>
                </div>
                <br/>
                <div>
                    <label for="spark">Operator radio:</label>
                    <select id="spark" name="id_spark">
                        <c:set var="j" value="0"/>
                        <c:forEach var="staff" items="${staffListForCrew}">
                            <c:if test="${staff.departamenId == 2}">
                                <c:set var="j" value="${j+1}"/>
                                <option value="${staff.id}">
                                        ${staff.firstName} ${staff.lastName}
                                </option>
                            </c:if>
                        </c:forEach>
                    </select>
                    <c:if test="${j==0}">
                        <c:set var="missed" value="Hooray"/>
                    </c:if>
                </div>
                <br/>
                <div>
                    <label for="steward">Steward/Stewardess:</label>
                    <select id="steward" name="id_steward">
                        <c:set var="g" value="0"/>
                        <c:forEach var="staff" items="${staffListForCrew}">
                            <c:if test="${staff.departamenId == 3}">
                                <c:set var="g" value="${g+1}"/>
                                <option value="${staff.id}">
                                        ${staff.firstName} ${staff.lastName}
                                </option>
                            </c:if>
                        </c:forEach>
                    </select>
                    <c:if test="${g==0}">
                        <c:set var="missed" value="Hooray"/>
                    </c:if>
                </div>
                <br/>
                <div>
                    <c:if test="${empty missed}">
                        <input type="hidden" name="command" value="saveCrew"/>
                        <input value="Send" type="submit"/>
                    </c:if>
                </div>
                <div>
                    <c:if test="${not empty missed}">
                        <input type="hidden" name="command" value="addRequest"/>
                        <input value="Request" type="submit"/>
                    </c:if>
                </div>
            </form>
            <%-- CONTENT --%>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>

</body>
</html>