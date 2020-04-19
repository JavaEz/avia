package ua.nure.moisieiev.summaryTask4.web.command.flightCommands;

import org.apache.log4j.Logger;
import ua.nure.moisieiev.summaryTask4.Path;
import ua.nure.moisieiev.summaryTask4.entity.Flight;
import ua.nure.moisieiev.summaryTask4.exception.AppException;
import ua.nure.moisieiev.summaryTask4.exception.Messages;
import ua.nure.moisieiev.summaryTask4.util.DBManager;
import ua.nure.moisieiev.summaryTask4.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightSearchCommand extends Command {

    private static final Logger LOG = Logger.getLogger(FlightSearchCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        int search = 0;
        Flight flight = null;
        List <Flight> flightList = new ArrayList<>();
        try{
       search = Integer.parseInt(request.getParameter("search"));
        } catch (NumberFormatException e) {
            LOG.error("MISTAKE! ID not a number");
        }
        LOG.trace("Request parameter: search --> " + search);
       if (search != 0){
           try {
               flight = DBManager.getInstance().findFlightById(search);
               LOG.trace("Find flight by id" + search);
           } catch (SQLException e) {
               e.printStackTrace();
           }
       } else {
           throw new AppException(Messages.ERR_CANNOT_GET_FLIGHT_BY_ID);
       }

       if(flight != null){
           flightList.add(flight);
       } else {
           throw new AppException(Messages.ERR_CANNOT_GET_FLIGHT_BY_ID);
       }

        request.getSession().setAttribute("flightList", flightList);
        LOG.trace("Set the request attribute: flightList --> " + flightList);

        LOG.debug("Command finished");
        return Path.PAGE_LIST_FLIGHT;
    }
}
