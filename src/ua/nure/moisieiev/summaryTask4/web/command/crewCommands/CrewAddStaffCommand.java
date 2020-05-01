package ua.nure.moisieiev.summaryTask4.web.command.crewCommands;

import org.apache.log4j.Logger;
import ua.nure.moisieiev.summaryTask4.Path;
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

public class CrewAddStaffCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CrewAddStaffCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        List<Staff> staffListForCrew = null;
        DBManager dbManager = DBManager.getInstance();
        try{
            staffListForCrew = dbManager.findAllFreeStaff();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("staffListForCrew", staffListForCrew);
        LOG.trace("Set the request attribute: staffListForCrew --> " + staffListForCrew);
        LOG.debug("Command finished");
        return Path.PAGE_ADD_CREW;
    }
}
