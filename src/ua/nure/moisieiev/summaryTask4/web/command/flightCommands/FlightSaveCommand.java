package ua.nure.moisieiev.summaryTask4.web.command.flightCommands;

import org.apache.log4j.Logger;
import ua.nure.moisieiev.summaryTask4.Path;
import ua.nure.moisieiev.summaryTask4.entity.Crew;
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
        String flightName = request.getParameter("flightName");
        LOG.trace("Request parameter: flightName --> " + flightName);
        String departure = request.getParameter("departure");
        LOG.trace("Request parameter: departure --> " + departure);
        String arrival = request.getParameter("arrival");
        LOG.trace("Request parameter: arrival --> " + arrival);
        String date = request.getParameter("date");
        LOG.trace("Request parameter: date --> " + date);
        String flightStatus = request.getParameter("flightStatus");
        LOG.trace("Request parameter: flightStatus --> " + flightStatus);
        String crewNumber = request.getParameter("crewNumber");
        LOG.trace("Request parameter: crewNumber --> " + crewNumber);

        DBManager dbManager = DBManager.getInstance();

        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("id_flight"));
        } catch (NumberFormatException e) {
            LOG.error("MISTAKE! ID not a number");
        }
        Flight flight;
        Crew crew;
        if (id != null) {
            try {
                flight = dbManager.findFlightById(id);
                flight.setFlightName(flightName);
                flight.setWhence(departure);
                flight.setWhereto(arrival);
                flight.setDate(Date.valueOf(date));
                flight.setFlightStatusId(Integer.parseInt(flightStatus));
                flight.setCrewId(Integer.parseInt(crewNumber));
                crew = dbManager.findCrewById(Integer.parseInt(crewNumber));
                crew.setCrewStatusId(1);
                dbManager.updateCrew(crew);
                dbManager.updateFlightById(flight);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Flight flight1 = new Flight();
                flight1.setFlightName(flightName);
                flight1.setWhence(departure);
                flight1.setWhereto(arrival);
                flight1.setDate(Date.valueOf(date));
                flight1.setFlightStatusId(4);
                //flight1.setCrewId(Integer.parseInt(crewNumber));
                dbManager.createFlight(flight1); //пишем новую логику
            } catch (SQLException e) {
                LOG.error("CANNOT CREATE A NEW FLIGHT");
            }
        }

        LOG.debug("Command finished");
        return Path.COMMAND_FLIGHT_LIST;
    }
}
