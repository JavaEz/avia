package ua.nure.moisieiev.summaryTask4.web.command.requestCommands;

import org.apache.log4j.Logger;
import ua.nure.moisieiev.summaryTask4.Path;
import ua.nure.moisieiev.summaryTask4.entity.Request;
import ua.nure.moisieiev.summaryTask4.exception.AppException;
import ua.nure.moisieiev.summaryTask4.util.DBManager;
import ua.nure.moisieiev.summaryTask4.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RequestChangeStatusCommand extends Command {

    private static final Logger LOG = Logger.getLogger(RequestChangeStatusCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        String requestStatus = request.getParameter("id_requestStatus");
        LOG.trace("Request parameter: requestStatus --> " + requestStatus);

        DBManager dbManager = DBManager.getInstance();
        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("id_request"));
        } catch (NumberFormatException e) {
            LOG.error("MISTAKE! ID not a number");
        }
        Request request1;
        if (id != null){
            try {
                request1 = dbManager.findRequestById(id);
                request1.setRequestStatusId(Integer.parseInt(requestStatus));
                dbManager.updateRequestStatusById(request1);
            } catch (SQLException e) {
                LOG.error("CANNOT UPDATE A REQUEST STATUS");
            }
        }
        LOG.debug("Command finished");
        return Path.COMMAND_REQUEST_LIST;
    }
}
