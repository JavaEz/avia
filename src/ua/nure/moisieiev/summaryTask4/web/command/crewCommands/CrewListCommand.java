package ua.nure.moisieiev.summaryTask4.web.command.crewCommands;

import org.apache.log4j.Logger;
import ua.nure.moisieiev.summaryTask4.Path;
import ua.nure.moisieiev.summaryTask4.entity.Crew;
import ua.nure.moisieiev.summaryTask4.entity.Flight;
import ua.nure.moisieiev.summaryTask4.entity.Staff;
import ua.nure.moisieiev.summaryTask4.exception.AppException;
import ua.nure.moisieiev.summaryTask4.util.DBManager;
import ua.nure.moisieiev.summaryTask4.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CrewListCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CrewListCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        //get crew staff flight lists
        List<Crew> crewList = null;
        List<Staff> staffList = null;
        List<Flight> flightList = null;
        try{
            DBManager dbManager = DBManager.getInstance();
            crewList = dbManager.findAllCrew();
            LOG.trace("Found in DB: crewList --> " + crewList);
            staffList = dbManager.findAllStaff();
            LOG.trace("Found in DB: staffList --> " + staffList);
            flightList = dbManager.findAllFlights();
            LOG.trace("Found in DB: flightList --> " + flightList);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(crewList == null || staffList == null){
            throw new AppException("Cannot get a crew list");
        }

        //put staff list, crew list, flight list to the request
        request.setAttribute("crewList", crewList);
        LOG.trace("Set the request attribute: crewList --> " + crewList);
        request.setAttribute("staffList", staffList);
        LOG.trace("Set the request attribute: staffList --> " + staffList);
        request.getSession().setAttribute("flightList", flightList);
        LOG.trace("Set the request attribute: flightList --> " + flightList);

        LOG.debug("Command finished");
        return Path.PAGE_LIST_CREW;

    }
}
