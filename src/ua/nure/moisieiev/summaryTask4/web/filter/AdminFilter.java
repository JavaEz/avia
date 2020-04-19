package ua.nure.moisieiev.summaryTask4.web.filter;

import org.apache.log4j.Logger;
import ua.nure.moisieiev.summaryTask4.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AdminFilter")
public class AdminFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(AdminFilter.class);

    public void destroy() {
        LOG.debug("Filter destruction starts");
        // no op
        LOG.debug("Filter destruction finished");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpSession session = null;
        if (req instanceof HttpServletRequest) {
            session = ((HttpServletRequest) req).getSession();
            Object object = session.getAttribute("user");
            if (object instanceof User) {
                User user = (User) object;
                int role = user.getUserRoleId();
                if (role == 0) {
                    chain.doFilter(req, resp);
                } else {
                    responseNotAllowed(resp);
                }
            } else {
                responseNotAllowed(resp);
            }
        }
    }

    private void responseNotAllowed(ServletResponse response) throws IOException {
        HttpServletResponse httpResp = (HttpServletResponse) response;
        httpResp.sendError(403, "You are not allowed! ");
    }

    public void init(FilterConfig config) throws ServletException {
        LOG.debug("Filter initialization starts");
        LOG.debug("Filter initialization finished");
    }

}
