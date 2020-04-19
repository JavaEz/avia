package ua.nure.moisieiev.summaryTask4.web.command.crewCommands;

import org.apache.log4j.Logger;
import ua.nure.moisieiev.summaryTask4.Path;
import ua.nure.moisieiev.summaryTask4.entity.Crew;
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

public class CrewEditCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CrewEditCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("id_crew"));
        } catch (NumberFormatException e) {
            LOG.error("MISTAKE! ID not a number");
        }

        LOG.trace("Request parameter: id --> " + id);
        Crew crew = null;
        List<Staff> freeStaffListForCrew = null;
        List<Staff> staffListForEdit = null;

        if(id != null){
            DBManager dbManager = DBManager.getInstance();
            try {
                crew = dbManager.findCrewById(id);
                freeStaffListForCrew = dbManager.findAllFreeStaff();
                staffListForEdit = dbManager.findAllStaffByCrewId(id);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getSession().setAttribute("crew", crew);
            LOG.trace("Set the request attribute: crew --> " + crew);
            request.getSession().setAttribute("freeStaffListForCrew", freeStaffListForCrew);
            LOG.trace("Set the request attribute: freeStaffListForCrew --> " + freeStaffListForCrew);
            request.getSession().setAttribute("staffListForEdit", staffListForEdit);
            LOG.trace("Set the request attribute: staffListForEdit --> " + staffListForEdit);

        }
        LOG.debug("Command finished");
        return Path.PAGE_EDIT_CREW;
    }
}
