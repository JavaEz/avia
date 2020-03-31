package ua.nure.moisieiev.summaryTask4.web.command.staffCommands;

import org.apache.log4j.Logger;
import ua.nure.moisieiev.summaryTask4.Path;
import ua.nure.moisieiev.summaryTask4.exception.AppException;
import ua.nure.moisieiev.summaryTask4.util.DBManager;
import ua.nure.moisieiev.summaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class StaffDeleteCommand extends Command {

    private static final Logger LOG = Logger.getLogger(StaffDeleteCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        Integer id = null;
        try{
            id = Integer.parseInt(request.getParameter("id_staff"));
        }catch (NumberFormatException e){
            LOG.error("MISTAKE! ID not a number");
        }

        LOG.trace("Request parameter: id --> " + id);
        if(id != null){
            DBManager dbManager = DBManager.getInstance();
            try {
                dbManager.deleteStaffById(id);
            }catch (SQLException e){
                LOG.error("CANNOT DELETE STAFF");
            }
        }
        return Path.COMMAND_STAFF_LIST;
    }
}
