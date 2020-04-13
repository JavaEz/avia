package ua.nure.moisieiev.summaryTask4.web.command.crewCommands;

import org.apache.log4j.Logger;
import ua.nure.moisieiev.summaryTask4.Path;
import ua.nure.moisieiev.summaryTask4.entity.Crew;
import ua.nure.moisieiev.summaryTask4.entity.Staff;
import ua.nure.moisieiev.summaryTask4.exception.AppException;
import ua.nure.moisieiev.summaryTask4.util.DBManager;
import ua.nure.moisieiev.summaryTask4.web.command.Command;
import ua.nure.moisieiev.summaryTask4.web.command.flightCommands.FlightSaveCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class СrewSaveCommand extends Command {

    private static final Logger LOG = Logger.getLogger(СrewSaveCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        int idPilot = Integer.parseInt(request.getParameter("id_pilot"));
        LOG.trace("Request parameter: idPilot --> " + idPilot);
        int idNavigator = Integer.parseInt(request.getParameter("id_navigator"));
        LOG.trace("Request parameter: idNavigator --> " + idNavigator);
        int idSpark = Integer.parseInt(request.getParameter("id_spark"));
        LOG.trace("Request parameter: idSpark --> " + idSpark);
        int idSteward = Integer.parseInt(request.getParameter("id_steward"));
        LOG.trace("Request parameter: idSteward --> " + idSteward);

        DBManager dbManager = DBManager.getInstance();
        Staff pilot;
        Staff navigator;
        Staff spark;
        Staff steward;
        try {
            Crew crew = new Crew();
            dbManager.createCrew(crew);
            int id = crew.getId();
                pilot = dbManager.findStaffById(idPilot);
                navigator = dbManager.findStaffById(idNavigator);
                spark = dbManager.findStaffById(idSpark);
                steward = dbManager.findStaffById(idSteward);
                pilot.setCrewId(id);
                navigator.setCrewId(id);
                spark.setCrewId(id);
                steward.setCrewId(id);
                dbManager.updateStaffById(pilot);
                dbManager.updateStaffById(navigator);
                dbManager.updateStaffById(spark);
                dbManager.updateStaffById(steward);
        } catch (SQLException e) {
            LOG.error("CANNOT CREATE A NEW CREW");
        }
        LOG.debug("Command finished");
        return Path.COMMAND_CREW_LIST;
    }
}
