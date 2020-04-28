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
import java.util.List;

public class RequestListCommand extends Command {

    private static final Logger LOG = Logger.getLogger(RequestListCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        //get request list
        List<Request> requestList = null;
        try {
            requestList = DBManager.getInstance().findAllRequests();
            LOG.trace("Found in DB: requestList --> " + requestList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // put request list to the request
        request.getSession().setAttribute("requestList", requestList);
        LOG.trace("Set the request attribute: requestList --> " + requestList);

        LOG.debug("Command finished");
        return Path.PAGE_LIST_REQUEST;

    }
}
