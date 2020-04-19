package ua.nure.moisieiev.summaryTask4.web.command.flightCommands;

import org.apache.log4j.Logger;
import ua.nure.moisieiev.summaryTask4.Path;
import ua.nure.moisieiev.summaryTask4.entity.Flight;
import ua.nure.moisieiev.summaryTask4.exception.AppException;
import ua.nure.moisieiev.summaryTask4.util.DBManager;
import ua.nure.moisieiev.summaryTask4.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class FlightSaveFlightStatusCommand extends Command {

    private static final Logger LOG = Logger.getLogger(FlightSaveFlightStatusCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        String flightStatus = request.getParameter("flightStatus");
        LOG.trace("Request parameter: flightStatus --> " + flightStatus);

        DBManager dbManager = DBManager.getInstance();

        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("id_flight"));
        } catch (NumberFormatException e) {
            LOG.error("MISTAKE! ID not a number");
        }
        Flight flight;
        if(id != null){
            try {
                flight = dbManager.findFlightById(id);
                flight.setFlightStatusId(Integer.parseInt(flightStatus));
                dbManager.updateFlightById(flight);
            } catch (SQLException e){
                LOG.error("CANNOT UPDATE A FLIGHT STATUS");
            }
        }
        LOG.debug("Command finished");
        return Path.COMMAND_FLIGHT_LIST;
    }
}
