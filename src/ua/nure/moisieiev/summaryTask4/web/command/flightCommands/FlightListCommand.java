package ua.nure.moisieiev.summaryTask4.web.command.flightCommands;

import org.apache.log4j.Logger;
import ua.nure.moisieiev.summaryTask4.Path;
import ua.nure.moisieiev.summaryTask4.entity.Flight;
import ua.nure.moisieiev.summaryTask4.exception.AppException;
import ua.nure.moisieiev.summaryTask4.util.DBManager;
import ua.nure.moisieiev.summaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class FlightListCommand extends Command {

    private static final Logger LOG = Logger.getLogger(FlightListCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");

        //get flights list

        List<Flight> flightList = null;
        try {
            flightList = DBManager.getInstance().findAllFlights();
            LOG.trace("Found in DB: flightList --> " + flightList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(flightList == null){
            throw new AppException("Cannot get a flight list");
        }

        // put flight list to the request
        request.setAttribute("flightList", flightList);
        LOG.trace("Set the request attribute: flightList --> " + flightList);

        LOG.debug("Command finished");
        return Path.PAGE_LIST_FLIGHT;

    }
}
