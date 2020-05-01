package ua.nure.moisieiev.summaryTask4.web.command.flightCommands;

import org.apache.log4j.Logger;
import ua.nure.moisieiev.summaryTask4.Path;
import ua.nure.moisieiev.summaryTask4.entity.Crew;
import ua.nure.moisieiev.summaryTask4.entity.Flight;
import ua.nure.moisieiev.summaryTask4.exception.AppException;
import ua.nure.moisieiev.summaryTask4.util.DBManager;
import ua.nure.moisieiev.summaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.*;

public class FlightListCommand extends Command {

    private static final Logger LOG = Logger.getLogger(FlightListCommand.class);

    private List<Flight> sortFlights(List <Flight> flights, String sorting, String ordering){
       switch (sorting){
           case "name":
               flights.sort(Comparator.comparing(Flight::getFlightName));
               if ("desc".equals(ordering)){
                   Collections.reverse(flights);
               }
               LOG.trace("Sort by name" + flights);
               break;
           case "number":
               flights.sort(Comparator.comparingInt(Flight::getId));
               if ("desc".equals(ordering)){
                   Collections.reverse(flights);
               }
               LOG.trace("Sort by number" + flights);
               break;
       }
       return flights;

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        //get flights list
        List<Flight> flightList = null;
        List<Crew> crewList = null;
        try {
            DBManager dbManager = DBManager.getInstance();
            flightList = dbManager.findAllFlights();
            LOG.trace("Found in DB: flightList --> " + flightList);
            crewList = dbManager.findAllFreeCrew();
            LOG.trace("Found in DB: crewList --> " + crewList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sorting = request.getParameter("sorting");
        String ordering = request.getParameter("ordering");
        if (sorting == null || ordering == null
                || sorting.isEmpty() || ordering.isEmpty()){
            LOG.trace("Fields is empty");
        } else {
           flightList = sortFlights(flightList, sorting, ordering);
        }
        // put flight list to the request
        request.getSession().setAttribute("flightList", flightList);
        LOG.trace("Set the request attribute: flightList --> " + flightList);
        request.getSession().setAttribute("crewList", crewList);
        LOG.trace("Set the request attribute: crewList --> " + crewList);

        LOG.debug("Command finished");
        return Path.PAGE_LIST_FLIGHT;

    }
}
