package ua.nure.moisieiev.summaryTask4.web.command.flightCommands;

import org.apache.log4j.Logger;
import ua.nure.moisieiev.summaryTask4.Path;
import ua.nure.moisieiev.summaryTask4.entity.Flight;
import ua.nure.moisieiev.summaryTask4.exception.AppException;
import ua.nure.moisieiev.summaryTask4.util.DBManager;
import ua.nure.moisieiev.summaryTask4.util.SearchHelper;
import ua.nure.moisieiev.summaryTask4.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightSelectionCommand extends Command {

    private static final Logger LOG = Logger.getLogger(FlightSelectionCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        List<Flight> flightList = new ArrayList<>();

        String from = request.getParameter("from");
        LOG.trace("Request parameter: from --> " + from);
        String to = request.getParameter("to");
        LOG.trace("Request parameter: to --> " + to);
        Date date = Date.valueOf(request.getParameter("date"));
        LOG.trace("Request parameter: date --> " + date);
        if (date != null && from != null && to != null) {
            from = SearchHelper.writeStringToDB(from);
            to = SearchHelper.writeStringToDB(to);
        }else {
            LOG.error("Cannot Find all Flight by Parameters");
        }
            try {
                flightList = DBManager.getInstance().findAllFlightsByParameters(from, to, date);
                LOG.trace("Find flight by parameters" + from + to + date);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        request.getSession().setAttribute("flightList", flightList);
        LOG.debug("Command finished");
        return Path.PAGE_LIST_FLIGHT;
    }
}
