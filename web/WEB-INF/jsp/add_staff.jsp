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
                    <label for="firstName"><fmt:message key="jsp.first.name"/>:</label>
                    <input type="text" name="firstName" required pattern="[a-zA-Z]{3,20}" id="firstName"/><br/>
                </div>
                <br/>
                <div>
                    <label for="lastName"><fmt:message key="jsp.last.name"/>:</label>
                    <input type="text" name="lastName" required pattern="[a-zA-Z]{3,20}" id="lastName"/><br/>
                </div>
                <br/>
                <div>
                    <label for="departamenId"><fmt:message key="jsp.department"/></label>
                    <select id="departamenId" name="departamenId" required>
                        <option value="1">
                            <fmt:message key="jsp.navigator"/>
                        </option>
                        <option value="2">
                            <fmt:message key="jsp.operator.radio"/>
                        </option>
                        <option value="3">
                            <fmt:message key="jsp.steward"/>
                        </option>
                        <option value="4">
                            <fmt:message key="jsp.pilot"/>
                        </option>
                    </select>
                </div>
                <br/>
                <div>
                        <input type="hidden" name="command" value="saveStaff"/>
                        <input type="hidden" name="id_staff"/>
                        <input value="<fmt:message key="jsp.send"/>" type="submit"/>
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
