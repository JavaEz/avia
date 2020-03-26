package ua.nure.moisieiev.summaryTask4.web.command.flightCommands;

import org.apache.log4j.Logger;
import ua.nure.moisieiev.summaryTask4.Path;
import ua.nure.moisieiev.summaryTask4.exception.AppException;
import ua.nure.moisieiev.summaryTask4.util.DBManager;
import ua.nure.moisieiev.summaryTask4.web.command.Command;
import ua.nure.moisieiev.summaryTask4.web.command.CommandContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class FlightDeleteCommand extends Command {

    private static final Logger LOG = Logger.getLogger(FlightDeleteCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        Integer id =  null;
        try{
        id = Integer.parseInt(request.getParameter("id_flight"));
        } catch(NumberFormatException e) {
            LOG.error("Id not number");
        }

        LOG.trace("Request parameter: id --> " + id);
        if (id != null ) {
            DBManager dbManager = DBManager.getInstance();
            try {
                dbManager.deleteFlightById(id);
            } catch (SQLException e) {
                LOG.error("CANNOT DELETE");
            }
        }

        return Path.COMMAND_FLIGHT_LIST;
    }
}
