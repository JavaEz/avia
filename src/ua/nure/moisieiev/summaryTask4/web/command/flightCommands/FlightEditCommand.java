package ua.nure.moisieiev.summaryTask4.web.command.flightCommands;

import org.apache.log4j.Logger;
import ua.nure.moisieiev.summaryTask4.Path;
import ua.nure.moisieiev.summaryTask4.entity.Flight;
import ua.nure.moisieiev.summaryTask4.exception.DBException;
import ua.nure.moisieiev.summaryTask4.util.DBManager;
import ua.nure.moisieiev.summaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class FlightEditCommand extends Command {

    private static final Logger LOG = Logger.getLogger(FlightEditCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        LOG.debug("Command starts");

        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("id_flight"));
        } catch (NumberFormatException e) {
            LOG.error("MISTAKE! ID not a number");
        }

        LOG.trace("Request parameter: id --> " + id);
        Flight flight = null;
        if (id != null) {
            DBManager dbManager = DBManager.getInstance();
            try {
                flight = dbManager.findFlightById(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getSession().setAttribute("flight", flight);
            LOG.trace("Set the request attribute: flightList --> " + flight);
        }

        LOG.debug("Command finished");
        return Path.PAGE_EDIT_FLIGHT;
}
}
