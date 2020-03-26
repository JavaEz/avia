package ua.nure.moisieiev.summaryTask4;

/**
 * Path holder (jsp pages, controller commands).
 * 
 * @author S.Moisieiev
 * 
 */
public final class Path {
	
	// pages
	public static final String PAGE_LOGIN = "/login.jsp";
	public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
	public static final String PAGE_LIST_FLIGHT = "/WEB-INF/jsp/flightlist.jsp";
	public static final String PAGE_EDIT_FLIGHT = "/WEB-INF/jsp/flight_edit.jsp";
	public static final String PAGE_SETTINGS = "/WEB-INF/jsp/settings.jsp";
	public static final String ADMIN_PAGE = "/WEB-INF/jsp/admin/admin.jsp";
	public static final String DISPATCHER_PAGE = "/WEB-INF/jsp/dispatcher/dispatcher.jsp";

	// commands

	public static final String COMMAND_FLIGHT_LIST = "/controller?command=flightList";
	public static final String COMMANND_EDIT_LIST = "/controller?command=editFlight";

}