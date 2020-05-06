<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<head>
    <title><fmt:message key="jsp.add.flight"/></title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content center">
            <%-- CONTENT --%>
            <form action="controller" method="post">
                <div>
                    <laber for="flightName"><fmt:message key="jsp.name"/>:</laber>
                    <input type="text" name="flightName" required pattern="[a-zA-Z]{3,20}"
                           id="flightName" /><br/>
                </div>
                <br/>
                <div>
                    <label for="departure"><fmt:message key="jsp.departure"/>:</label>
                    <input type="text" name="departure" required pattern="[a-zA-Z]{3,20}"
                           id="departure"/><br/>
                </div>
                <br/>
                <div>
                    <label for="arrival"><fmt:message key="jsp.destination"/>:</label>
                    <input type="text" name="arrival" required pattern="[a-zA-Z]{3,20}"
                           id="arrival"/><br/>
                </div>
                <br/>
                <div>
                    <label for="date"><fmt:message key="jsp.date.of.departure"/></label>
                    <input type="date" name="date" required
                           id="date"/><br/>
                </div>
                <br/>
                <div>
                    <input type="hidden" name="command" value="saveFlight"/>
                    <input value="<fmt:message key="jsp.send"/>" type="submit"/>
                </div>
            </form>
            <%-- CONTENT --%>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>

</body>
</html>
