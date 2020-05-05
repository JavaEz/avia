package ua.nure.moisieiev.summaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.moisieiev.summaryTask4.Path;
import ua.nure.moisieiev.summaryTask4.entity.User;
import ua.nure.moisieiev.summaryTask4.exception.AppException;
import ua.nure.moisieiev.summaryTask4.util.DBManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class ViewSettingsCommand extends Command{

    private static final String ENGLISH = "English";
    private static final String RUSSIAN = "Russian";

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(ViewSettingsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        User user = (User) request.getSession().getAttribute("user");
        LOG.trace("Get user from session: " + user);
        String language = request.getParameter("locale");
        LOG.trace("Get parameters -> locale " + language);

        Locale loc;
        switch (language) {
            default:
            case ENGLISH:
                loc = new Locale("en");
                request.getSession().setAttribute("locale", "en");
                LOG.debug("Set locale " + ENGLISH);
                break;
            case RUSSIAN:
                loc = new Locale("ru");
                request.getSession().setAttribute("locale", "ru");
                LOG.debug("Set locale " + RUSSIAN);
                break;
        }
        Locale.setDefault(loc);
        LOG.debug("Set locale " + loc);

        LOG.debug("Command finished");
        return Path.PAGE_SETTINGS;
    }
}
