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
import java.util.List;

public class StaffListCommand extends Command {

    private static final Logger LOG = Logger.getLogger(StaffListCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");

        //get staff list

        List<Staff> staffList = null;
        try {
            staffList = DBManager.getInstance().findAllStaff();
            LOG.trace("Found in DB: staffList --> " + staffList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (staffList == null) {
            throw new AppException("Cannot get a staff list");
        }

        //put staff list to the request
        request.setAttribute("staffList", staffList);
        LOG.trace("Set the request attribute: staffList --> " + staffList);

        LOG.debug("Command finished");
        return Path.PAGE_LIST_STAFF;
    }
}
