<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<c:set var="title" value="Staff team" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>
            <form id="create_crew" action="controller" method="post">
                <input type="hidden" name="command" value="editCrew"/>
                <input value="Add new Crew" type="submit"/>
            </form>

            <table id="list_staff_table">
                <thead>
                <tr>
                    <td>ID</td>
                    <td>Crew Status</td>
                </tr>
                </thead>

                <c:forEach var="crew" items="${crewList}">
                    <tr>
    <td>${crew.id}</td>
    <td><c:if test="${crew.crewStatusId == 0}">Not Ready</c:if>
        <c:if test="${crew.crewStatusId == 1}">Ready</c:if></td>
    <c:if test="${userRole.name == 'dispatcher'}">
            <td class="content center">
                <form id="edit_crew" action="controller" method="post">
                    <input type="hidden" name="command" value="editCrew"/>
                    <input type="hidden" name="id_crew" value="${crew.id}"/>
                    <input type="submit" value="Edit">
                </form>
            </td>
            <td class="content center">
                 <form id="delete_crew" action="controller" method="post">
                     <input type="hidden" name="command" value="deleteCrew"/>
                     <input type="hidden" name="id_crew" value="${crew.id}"/>
                     <input type="submit" value="Delete">
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
