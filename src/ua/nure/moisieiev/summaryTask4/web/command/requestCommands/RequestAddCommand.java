package ua.nure.moisieiev.summaryTask4.web.command.requestCommands;

import org.apache.log4j.Logger;
import ua.nure.moisieiev.summaryTask4.Path;
import ua.nure.moisieiev.summaryTask4.entity.Request;
import ua.nure.moisieiev.summaryTask4.exception.AppException;
import ua.nure.moisieiev.summaryTask4.util.DBManager;
import ua.nure.moisieiev.summaryTask4.web.command.Command;
import ua.nure.moisieiev.summaryTask4.web.command.crewCommands.CrewAddStaffCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RequestAddCommand extends Command {

    private static final Logger LOG = Logger.getLogger(RequestAddCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {


        LOG.debug("Command starts");
        String idPilot = request.getParameter("id_pilot");
        LOG.trace("Request parameter: idPilot --> " + idPilot);
        String idNavigator = request.getParameter("id_navigator");
        LOG.trace("Request parameter: idNavigator --> " + idNavigator);
        String idSpark = request.getParameter("id_spark");
        LOG.trace("Request parameter: idSpark --> " + idSpark);
        String idSteward = request.getParameter("id_steward");
        LOG.trace("Request parameter: idSteward --> " + idSteward);


        DBManager dbManager = DBManager.getInstance();
        try {
            Request addRequest = new Request();
            if (idPilot != null && !idPilot.isEmpty()) {
                addRequest.setIdPilot(Integer.parseInt(idPilot));
            }
            if (idNavigator != null && !idNavigator.isEmpty()) {
                addRequest.setIdNavigator(Integer.parseInt(idNavigator));
            }
            if (idSpark != null && !idSpark.isEmpty()) {
                addRequest.setIdSpark(Integer.parseInt(idSpark));
            }
            if (idSteward != null && !idSteward.isEmpty()) {
                addRequest.setIdSteward(Integer.parseInt(idSteward));
            }
            dbManager.createRequest(addRequest);
        } catch (SQLException e) {
            LOG.error("CANNOT CREATE A REQUEST");
        }

        LOG.debug("Command finished");
        return Path.COMMAND_REQUEST_LIST;
    }
}
