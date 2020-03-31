package ua.nure.moisieiev.summaryTask4.web.command.staffCommands;

import org.apache.log4j.Logger;
import ua.nure.moisieiev.summaryTask4.Path;
import ua.nure.moisieiev.summaryTask4.entity.Staff;
import ua.nure.moisieiev.summaryTask4.exception.AppException;
import ua.nure.moisieiev.summaryTask4.util.DBManager;
import ua.nure.moisieiev.summaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class StaffEditCommand extends Command {

    private static final Logger LOG = Logger.getLogger(StaffEditCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws  AppException {
        LOG.debug("Command starts");

        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("id_staff"));
        } catch (NumberFormatException e) {
            LOG.error("MISTAKE! ID not a number");
        }

        LOG.trace("Request parameter: id --> " + id);
        Staff staff = null;
        if (id != null) {
            DBManager dbManager = DBManager.getInstance();
            try {
                staff = dbManager.findStaffById(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getSession().setAttribute("staff", staff);
            LOG.trace("Set the request attribute: staff --> " + staff);
        }
        LOG.debug("Command finished");
        return Path.PAGE_EDIT_STAFF;
    }
}
