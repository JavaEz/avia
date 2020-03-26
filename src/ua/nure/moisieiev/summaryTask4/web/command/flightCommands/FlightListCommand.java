package ua.nure.moisieiev.summaryTask4.web.command.flightCommands;

import org.apache.log4j.Logger;
import ua.nure.moisieiev.summaryTask4.Path;
import ua.nure.moisieiev.summaryTask4.entity.Flight;
import ua.nure.moisieiev.summaryTask4.exception.AppException;
import ua.nure.moisieiev.summaryTask4.util.DBManager;
import ua.nure.moisieiev.summaryTask4.web.command.Command;
import ua.nure.moisieiev.summaryTask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class FlightListCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        //get flights list

        List<Flight> flightList = null;
        try {
            flightList = DBManager.getInstance().findAllFlights();
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
