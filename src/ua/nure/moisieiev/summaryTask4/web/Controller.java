package ua.nure.moisieiev.summaryTask4.web;

import org.apache.log4j.Logger;
import ua.nure.moisieiev.summaryTask4.Path;
import ua.nure.moisieiev.summaryTask4.exception.AppException;
import ua.nure.moisieiev.summaryTask4.web.command.Command;
import ua.nure.moisieiev.summaryTask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Main servlet controller.
 *
 * @author S.Moisieiev
 *
 */
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 2423353715955164816L;

    private static final Logger LOG = Logger.getLogger(Controller.class);

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        process(request, response, ActionType.GET);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        process(request, response, ActionType.POST);
    }

    /**
     * Main method of this controller.
     */
    private void process(HttpServletRequest request,
                         HttpServletResponse response, ActionType actionType) throws IOException, ServletException {

        LOG.debug("Controller starts");

        // extract command name from the request
        String commandName = request.getParameter("command"); //
        LOG.trace("Request parameter: command --> " + commandName);

        // get command object by its name
        Command command = CommandContainer.get(commandName);
        LOG.trace("Obtained command --> " + command);
        //request.getContextPath();
        // execute command and get forward address
        String forward = Path.PAGE_ERROR_PAGE;
        try {
            forward = command.execute(request, response);
        } catch (AppException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
        }
        LOG.trace("Forward address --> " + forward);

        LOG.debug("Controller finished, now go to forward address --> " + forward);

        //go to forward
        //request.getRequestDispatcher(forward).forward(request, response);
        //response.sendRedirect(forward);
        if(actionType == ActionType.GET){
            request.getRequestDispatcher(forward).forward(request, response); // отправляет данные через гет запрос
        } else if (actionType == ActionType.POST){
            response.sendRedirect(forward);  //просот редирект на сттраницу без отправки данных
        }
    }

}
