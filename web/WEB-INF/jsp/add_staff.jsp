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
                    <input type="text" name="firstName" required id="firstName"/><br/>
                </div>
                <br/>
                <div>
                    <label for="lastName">First name:</label>
                    <input type="text" name="lastName" required id="lastName"/><br/>
                </div>
                <br/>
                <div>
                    <label for="departamenId">Departament</label>
                    <input type="number" name="departamenId" min="1" max="4" required
                           id="departamenId"/><br/>
                </div>
                <br/>
                <div>
                    <div>
                        <label for="crewId">crewId</label>
                        <input type="number" name="crewId" required
                               id="crewId"/><br/>
                    </div>
                    <br/>
                    <div>
                        <input type="hidden" name="command" value="saveStaff"/>
                        <input type="hidden" name="id_staff"/>
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
