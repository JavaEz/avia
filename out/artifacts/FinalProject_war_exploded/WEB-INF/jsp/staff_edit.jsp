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
                        <legend>First name:</legend>
                        <input type="text" name="firstName" id="firstName"
                               value="${staff.firstName}"/><br/>
                    </div><br/>
                    <div>
                        <legend>First name:</legend>
                        <input type="text" name="lastName" id="lastName"
                               value="${staff.lastName}"/><br/>
                    </div><br/>
                    <div>
                        <legend>Departament</legend>
                        <input type="number" name="departamenId" min="0" max="3"
                        id="departamenId" value="${staff.departamenId}"/><br/>
                    </div><br/>
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
