package ua.nure.moisieiev.summaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.moisieiev.summaryTask4.Path;
import ua.nure.moisieiev.summaryTask4.entity.Role;
import ua.nure.moisieiev.summaryTask4.entity.User;
import ua.nure.moisieiev.summaryTask4.exception.AppException;
import ua.nure.moisieiev.summaryTask4.util.DBManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Login command.
 *
 * @author S.Moisieiev
 */

public class LoginCommand extends Command {

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        HttpSession session = request.getSession();

        //get login and password from request
        DBManager manager = DBManager.getInstance();
        String login = request.getParameter("login");
        LOG.trace("Request parameter: login --> " + login);

        String password = request.getParameter("password");
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            throw new AppException("Login/password cannot be empty");
        }

        User user = null;
        try {
            user = manager.findUserByLogin(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LOG.trace("Found in DB: user --> " + user);

        if (user == null || !password.equals(user.getPassword())) {
            throw new AppException("Cannot find user with such login/password");
        }

        Role userRole = Role.getRole(user);
        LOG.trace("get userRole --> " + userRole);

        String forward = Path.PAGE_ERROR_PAGE;

        if (userRole == Role.ADMIN) {
            forward = Path.COMMAND_FLIGHT_LIST;
        }

        if (userRole == Role.DISPATCHER) {
            forward = Path.COMMAND_FLIGHT_LIST;
        }

        session.setAttribute("user", user);
        		LOG.trace("Set the session attribute: user --> " + user);

        		session.setAttribute("userRole", userRole);
        		LOG.trace("Set the session attribute: userRole --> " + userRole);

        		LOG.info("User " + user + " logged as " + userRole.toString().toLowerCase());

        		LOG.debug("Command finished");
        		return forward;
    }
}
