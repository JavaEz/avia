package ua.nure.moisieiev.summaryTask4.web.command.flightCommands;

import org.apache.log4j.Logger;
import ua.nure.moisieiev.summaryTask4.Path;
import ua.nure.moisieiev.summaryTask4.entity.Flight;
import ua.nure.moisieiev.summaryTask4.exception.DBException;
import ua.nure.moisieiev.summaryTask4.util.DBManager;
import ua.nure.moisieiev.summaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;

public class FlightSaveCommand extends Command {

    private static final Logger LOG = Logger.getLogger(FlightSaveCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        LOG.debug("Command starts");
        DBManager dbManager = DBManager.getInstance();

        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("id_flight"));
        } catch (NumberFormatException e) {
            LOG.error("Id not number");
        }
        Flight flight = new Flight();
        if (id != null) {
        try{
            flight = dbManager.findFlightById(id);
        }catch(SQLException e) {
            e.printStackTrace();
        }
        }else{
            flight.setFlightStatusId(2);
        }
        flight.setFlightName(request.getParameter("flightName"));
        flight.setWhence(request.getParameter("departure"));
        flight.setWhereto(request.getParameter("arrival"));
        flight.setDate(Date.valueOf(request.getParameter("date")));
        flight.setFlightStatusId(Integer.parseInt(request.getParameter("flightStatus")));
        flight.setCrewId(Integer.parseInt(request.getParameter("crewNumber")));

        if (flight.getFlightName() != null && flight.getWhence() != null
                && flight.getWhereto()!=null && flight.getDate()!=null
                &&(Integer) flight.getFlightStatusId() !=null && (Integer)flight.getCrewId() !=null){
            try {
                dbManager.updateFlightById(flight);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        LOG.debug("Command finished");
        return Path.COMMAND_FLIGHT_LIST;
    }
}
