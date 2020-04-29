package ua.nure.moisieiev.summaryTask4.web.command.requestCommands;

import org.apache.log4j.Logger;
import ua.nure.moisieiev.summaryTask4.Path;
import ua.nure.moisieiev.summaryTask4.entity.Request;
import ua.nure.moisieiev.summaryTask4.entity.RequestStatus;
import ua.nure.moisieiev.summaryTask4.exception.AppException;
import ua.nure.moisieiev.summaryTask4.util.DBManager;
import ua.nure.moisieiev.summaryTask4.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RequestEditCommand extends Command {

    private static final Logger LOG = Logger.getLogger(RequestEditCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("id_request"));
        } catch (NumberFormatException e) {
            LOG.error("MISTAKE! ID not a number");
        }

        LOG.trace("Request parameter: id --> " + id);
        Request request1 = null;
        if (id != null) {
            DBManager dbManager = DBManager.getInstance();
            try {
                request1 = dbManager.findRequestById(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            List<String> requestStatusesList = Stream.of(RequestStatus.values()).map(RequestStatus::getName).collect(Collectors.toList());
            request.getSession().setAttribute("requestStatusesList", requestStatusesList);
            LOG.trace("Set the request attribute: requestStatusesList --> " + requestStatusesList);
            request.getSession().setAttribute("request1", request1);
            LOG.trace("Set the request attribute: request1 --> " + request1);
        }


        LOG.debug("Command finished");
        return Path.PAGE_EDIT_REQUEST;
    }
}
