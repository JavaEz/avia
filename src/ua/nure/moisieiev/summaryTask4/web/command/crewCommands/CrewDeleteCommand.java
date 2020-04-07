package ua.nure.moisieiev.summaryTask4.web.command.crewCommands;

import org.apache.log4j.Logger;
import ua.nure.moisieiev.summaryTask4.Path;
import ua.nure.moisieiev.summaryTask4.exception.AppException;
import ua.nure.moisieiev.summaryTask4.util.DBManager;
import ua.nure.moisieiev.summaryTask4.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CrewDeleteCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CrewDeleteCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        Integer id = null;
        try{
            id = Integer.parseInt(request.getParameter("id_crew"));
        }catch (NumberFormatException e){
            LOG.error("MISTAKE! ID not a number");
        }

        LOG.trace("Request parameter: id --> " + id);
        if (id != null){
            DBManager dbManager = DBManager.getInstance();
            try {
                dbManager.deleteCrewById(id);
            }catch (SQLException e){
                LOG.error("CANNOT DELETE CREW");
            }
        }
        return Path.COMMAND_CREW_LIST;
    }
}
