package ua.nure.moisieiev.summaryTask4.util;

import org.apache.log4j.Logger;
import ua.nure.moisieiev.summaryTask4.entity.*;
import ua.nure.moisieiev.summaryTask4.exception.DBException;
import ua.nure.moisieiev.summaryTask4.exception.Messages;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private static final Logger LOG = Logger.getLogger(DBManager.class);

    // //////////////////////////////////////////////////////////
    // singleton
    // //////////////////////////////////////////////////////////

    private static DBManager instance;

    public static synchronized DBManager getInstance() throws DBException {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private DBManager() throws DBException {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            // FINALTASK - the name of data source
            ds = (DataSource) envContext.lookup("jdbc/FINALTASK");
            LOG.trace("Data source ==> " + ds);
        } catch (NamingException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
        }
    }

    private DataSource ds;

    public Connection getConnection() throws DBException {
        Connection con = null;
        try {
            con = ds.getConnection();
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
        }
        return con;
    }

    // //////////////////////////////////////////////////////////
    // SQL
    // //////////////////////////////////////////////////////////

    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
    private static final String SQL_FIND_ALL_FLIGHTS = "SELECT * FROM flights";
    private static final String SQL_DELETE_FLIGHT_BY_ID = "DELETE FROM flights WHERE id = ?";
    private static final String SQL_FIND_FLIGHT_BY_ID = "SELECT * FROM flights WHERE id = ?";
    private static final String SQL_FIND_ALL_FLIGHTS_BY_PARAM = "SELECT * FROM flights WHERE whence = ? AND whereto = ? AND date = ?";
    private static final String SQL_UPDATE_FLIGHT_BY_ID = "UPDATE flights SET flight_name = ?, whence = ?," +
            "whereto = ?, date = ?, flight_status = ?, crew_id = ? WHERE id = ?";
    private static final String SQL_CREATE_FLIGHT = "INSERT INTO flights" +
            "(flight_name, whence, whereto, date, flight_status, crew_id)" +
            "VALUES(?, ?, ?, ?, ?, ? )";
    private static final String SQL_FIND_ALL_STAFF = "SELECT * FROM staff";
    private static final String SQL_DELETE_STAFF_BY_ID = "DELETE FROM staff WHERE id = ?";
    private static final String SQL_FIND_STAFF_BY_ID = "SELECT * FROM staff WHERE id = ?";
    private static final String SQL_UPDATE_STAFF_BY_ID = "UPDATE staff SET staff_fname = ?, staff_lname = ?," +
            "departament_id = ?, crew_id = ? WHERE id = ?";
    private static final String SQL_CREATE_STAFF = "INSERT INTO STAFF (staff_fname, staff_lname, departament_id, crew_id)" +
            "VALUES(?, ?, ?, ?)";
    private static final String SQL_FIND_ALL_CREW = "SELECT * FROM crew";
    private static final String SQL_DELETE_CREW_BY_ID = "DELETE FROM crew WHERE id = ?";
    private static final String SQL_FIND_CREW_BY_ID = "SELECT * FROM crew WHERE id = ?";
    private static final String SQL_SET_DEFAULT_CREW_TO_STAFF = "UPDATE staff SET staff.crew_id = 0 WHERE staff.crew_id = ?";
    private static final String SQL_FIND_ALL_FREE_STAFF = "SELECT * FROM staff WHERE staff.crew_id = 0";
    private static final String SQL_CREATE_CREW = "INSERT INTO crew VALUES(default, default)";
    private static final String SQL_FIND_ALL_STAFF_BY_CREW_ID = "SELECT * FROM staff WHERE staff.crew_id = ?";
    private static final String SQL_FIND_ALL_REQUESTS = "SELECT * FROM request";
    private static final String SQL_CREATE_REQUEST = "INSERT INTO request VALUES(default, ?, ?, ?, ?, default)";
    private static final String SQL_FIND_REQUEST_BY_ID = "SELECT * FROM request WHERE id = ?";
    private static final String SQL_UPDATE_REQUEST_STATUS = "UPDATE request SET request_status = ? WHERE id = ?";
    private static final String SQL_FIND_ALL_FREE_CREW = "SELECT * FROM crew WHERE crew.crewstatus_id = 3";
    private static final String SQL_UPDATE_CREW_BY_ID = "UPDATE crew SET crewstatus_id = ? WHERE id = ?";
    private static final String SQL_FIND_FLIGHT_BY_CREW_ID = "SELECT * FROM flights WHERE flights.crew_id = ?";

    /**
     * Returns a user with the given login.
     *
     * @param login User login.
     * @return User entity.
     * @throws DBException
     */

    public User findUserByLogin(String login) throws DBException, SQLException {
        User user = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            statement = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            statement.setString(1, login);
            rs = statement.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, e);
        } finally {
            close(con, statement, rs);
        }
        return user;
    }

    /**
     * Returns all flight.
     *
     * @return List of flight entities.
     */
    public List<Flight> findAllFlights() throws DBException, SQLException {
        List<Flight> flightList = new ArrayList<Flight>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_FIND_ALL_FLIGHTS);
            while (rs.next()) {
                flightList.add(extractFlight(rs));
            }
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            LOG.error(Messages.ERR_CANNOT_GET_ALL_FLIGHTS, e);
            throw new DBException(Messages.ERR_CANNOT_GET_ALL_FLIGHTS, e);
        } finally {
            close(con, stmt, rs);
        }
        return flightList;
    }

    /**
     * Delete flight by flight id.
     *
     * @param id;
     */

    public void deleteFlightById(int id) throws DBException, SQLException {
        PreparedStatement statement = null;
        Connection con = null;
        try {
            con = getConnection();
            statement = con.prepareStatement(SQL_DELETE_FLIGHT_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            LOG.error(Messages.ERR_CANNOT_DELETE_FLIGHT);
            throw new DBException(Messages.ERR_CANNOT_DELETE_FLIGHT, e);
        } finally {
            close(con);
            close(statement);
        }
    }

    /**
     * Find flight by flight id.
     *
     * @param id;
     */

    public Flight findFlightById(int id) throws DBException, SQLException {
        Flight flight = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            statement = con.prepareStatement(SQL_FIND_FLIGHT_BY_ID);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                flight = extractFlight(rs);
            }
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            LOG.error(Messages.ERR_CANNOT_GET_FLIGHT_BY_ID, e);
            throw new DBException(Messages.ERR_CANNOT_GET_FLIGHT_BY_ID, e);
        } finally {
            close(con, statement, rs);
        }
        return flight;
    }

    /**
     * Find flight by crew id.
     *
     * @param id;
     */

    public Flight findFLightByCrewId(int id) throws SQLException, DBException {
        Flight flight = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            statement = con.prepareStatement(SQL_FIND_FLIGHT_BY_CREW_ID);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                flight = extractFlight(rs);
            }
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            LOG.error(Messages.ERR_CANNOT_GET_FLIGHT_BY_CREW_ID, e);
            throw new DBException(Messages.ERR_CANNOT_GET_FLIGHT_BY_CREW_ID, e);
        } finally {
            close(con, statement, rs);
        }
        return flight;
    }

    /**
     * Update flight.
     *
     * @param flight;
     */

    public void updateFlightById(Flight flight) throws SQLException, DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_UPDATE_FLIGHT_BY_ID);
            int k = 1;
            pstmt.setString(k++, flight.getFlightName());
            pstmt.setString(k++, flight.getWhence());
            pstmt.setString(k++, flight.getWhereto());
            pstmt.setDate(k++, flight.getDate());
            pstmt.setInt(k++, flight.getFlightStatusId());
            pstmt.setInt(k++, flight.getCrewId());
            pstmt.setInt(k, flight.getId());
            pstmt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            LOG.error(Messages.ERR_CANNOT_UPDATE_FLIGHT);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_FLIGHT, e);
        } finally {
            close(con);
            close(pstmt);
        }
    }

    /**
     * Create flight.
     *
     * @param flight;
     */

    public void createFlight(Flight flight) throws DBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_CREATE_FLIGHT, Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            pstmt.setString(k++, flight.getFlightName());
            pstmt.setString(k++, flight.getWhence());
            pstmt.setString(k++, flight.getWhereto());
            pstmt.setDate(k++, flight.getDate());
            pstmt.setInt(k++, flight.getFlightStatusId());
            pstmt.setInt(k, flight.getCrewId());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                flight.setId(rs.getInt(1));
            }
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            LOG.error(Messages.ERR_CANNOT_CREATE_FLIGHT);
            throw new DBException(Messages.ERR_CANNOT_CREATE_FLIGHT, e);
        } finally {
            close(con);
            close(pstmt);
        }
    }

    /**
     * Returns all staff list.
     *
     * @return List of staff entities.
     */

    public List<Staff> findAllStaff() throws SQLException, DBException {
        List<Staff> staffList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_FIND_ALL_STAFF);
            while (rs.next()) {
                staffList.add(extractStaff(rs));
            }
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            LOG.error(Messages.ERR_CANNOT_GET_ALL_STAFF, e);
            throw new DBException(Messages.ERR_CANNOT_GET_ALL_STAFF, e);
        } finally {
            close(con, stmt, rs);
        }
        return staffList;
    }

    /**
     * Delete staff by staff id.
     *
     * @param id;
     */

    public void deleteStaffById(int id) throws DBException, SQLException {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_STAFF_BY_ID);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            LOG.error(Messages.ERR_CANNOT_DELETE_STAFF);
            throw new DBException(Messages.ERR_CANNOT_DELETE_STAFF, e);
        } finally {
            close(con);
            close(pstmt);
        }
    }

    /**
     * Find staff by staff id.
     *
     * @param id;
     */

    public Staff findStaffById(int id) throws DBException, SQLException {
        Staff staff = null;
        PreparedStatement pstmt = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_STAFF_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                staff = extractStaff(rs);
            }
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            LOG.error(Messages.ERR_CANNOT_GET_STAFF_BY_ID);
            throw new DBException(Messages.ERR_CANNOT_GET_STAFF_BY_ID, e);
        } finally {
            close(con, pstmt, rs);
        }
        return staff;
    }

    /**
     * Update staff.
     *
     * @param staff;
     */

    public void updateStaff(Staff staff) throws DBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_UPDATE_STAFF_BY_ID);
            int k = 1;
            pstmt.setString(k++, staff.getFirstName());
            pstmt.setString(k++, staff.getLastName());
            pstmt.setInt(k++, staff.getDepartamenId());
            pstmt.setInt(k++, staff.getCrewId());
            pstmt.setInt(k, staff.getId());
            pstmt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            LOG.error(Messages.ERR_CANNOT_UPDATE_STAFF);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_STAFF, e);
        } finally {
            close(con);
            close(pstmt);
        }
    }

    /**
     * Create staff.
     *
     * @param staff;
     */

    public void createStaff(Staff staff) throws SQLException, DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_CREATE_STAFF);
            int k = 1;
            pstmt.setString(k++, staff.getFirstName());
            pstmt.setString(k++, staff.getLastName());
            pstmt.setInt(k++, staff.getDepartamenId());
            pstmt.setInt(k, staff.getCrewId());
            pstmt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            LOG.error(Messages.ERR_CANNOT_CREATE_STAFF);
            throw new DBException(Messages.ERR_CANNOT_CREATE_STAFF, e);
        } finally {
            close(con);
            close(pstmt);
        }
    }

    /**
     * Returns all crew list.
     *
     * @return List of crew entities.
     */
    public List<Crew> findAllCrew() throws DBException, SQLException {
        List<Crew> crewList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_FIND_ALL_CREW);
            while (rs.next()) {
                crewList.add(extractCrew(rs));
            }
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            LOG.error(Messages.ERR_CANNOT_GET_ALL_CREW);
            throw new DBException(Messages.ERR_CANNOT_GET_ALL_CREW, e);
        } finally {
            close(con, stmt, rs);
        }
        return crewList;
    }

    public List<Staff> findAllFreeStaff() throws DBException, SQLException {
        List<Staff> staffList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_FIND_ALL_FREE_STAFF);
            while (rs.next()) {
                staffList.add(extractStaff(rs));
            }
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            LOG.error("Cannot find free staff", e);
            throw new DBException("Cannot find free staff", e);
        } finally {
            close(con, stmt, rs);
        }
        return staffList;
    }

    /**
     * Delete crew by crew id.
     *
     * @param id;
     */
    public void deleteCrewById(int id) throws DBException, SQLException {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_CREW_BY_ID);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            LOG.error(Messages.ERR_CANNOT_DELETE_CREW);
            throw new DBException(Messages.ERR_CANNOT_DELETE_CREW, e);
        } finally {
            close(con);
            close(pstmt);
        }
    }

    /**
     * Find crew by crew id.
     *
     * @param id;
     */

    public Crew findCrewById(int id) throws SQLException, DBException {
        Crew crew = null;
        PreparedStatement pstmt = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_CREW_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                crew = extractCrew(rs);
            }
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            LOG.error(Messages.ERR_CANNOT_GET_CREW_BY_ID);
            throw new DBException(Messages.ERR_CANNOT_GET_CREW_BY_ID, e);
        } finally {
            close(con, pstmt, rs);
        }
        return crew;
    }

    public void addDefaultCrewToStaff(int id) throws DBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_SET_DEFAULT_CREW_TO_STAFF);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            LOG.error("CANT SET DEFAULT CREW TO STAFF");
            throw new DBException(Messages.ERR_CANNOT_UPDATE_STAFF, e);
        } finally {
            close(con);
            close(pstmt);
        }
    }

    /**
     * Create crew.
     *
     * @param crew;
     */

    public void createCrew(Crew crew) throws DBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_CREATE_CREW, Statement.RETURN_GENERATED_KEYS);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                crew.setId(rs.getInt(1));
            }
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            LOG.error("CANNOT CREATE CREW");
            throw new DBException("CANNOT CREATE CREW", e);
        } finally {
            close(con);
            close(pstmt);
        }
    }

    /**
     * Update crew.
     *
     * @param crew;
     */

    public void updateCrew(Crew crew) throws SQLException, DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try{
            con = getConnection();
            pstmt = con.prepareStatement(SQL_UPDATE_CREW_BY_ID);
            int k = 1;
            pstmt.setInt(k++, crew.getCrewStatusId());
            pstmt.setInt(k, crew.getId());
            pstmt.executeUpdate();
            con.commit();
        } catch (SQLException e){
            con.rollback();
            LOG.error(Messages.ERR_CANNOT_UPDATE_CREW);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_CREW, e);
        } finally {
            close(con);
            close(pstmt);
        }
    }

    /**
     * Find staff by crew id in staff.
     *
     * @param id;
     */

    public List<Staff> findAllStaffByCrewId(int id) throws SQLException, DBException {
        List<Staff> staffList = new ArrayList<>();
        PreparedStatement pstmt = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_ALL_STAFF_BY_CREW_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                staffList.add(extractStaff(rs));
            }
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            LOG.error(Messages.ERR_CANNOT_GET_STAFF_LIST_BY_CREW_ID, e);
            throw new DBException(Messages.ERR_CANNOT_GET_STAFF_LIST_BY_CREW_ID, e);
        } finally {
            close(con, pstmt, rs);
        }
        return staffList;
    }


    public List<Flight> findAllFlightsByParameters(String from, String to, Date date) throws DBException, SQLException {
        List<Flight> flightList = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            int k = 1;
            con = getConnection();
            statement = con.prepareStatement(SQL_FIND_ALL_FLIGHTS_BY_PARAM);
            statement.setString(k++, from);
            statement.setString(k++, to);
            statement.setDate(k, date);
            rs = statement.executeQuery();
            while (rs.next()) {
                flightList.add(extractFlight(rs));
            }
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            LOG.error(Messages.ERR_CANNOT_GET_ALL_FLIGHTS_BY_PARAM);
            throw new DBException(Messages.ERR_CANNOT_GET_ALL_FLIGHTS_BY_PARAM, e);
        } finally {
            close(con, statement, rs);
        }
        return flightList;
    }

    /**
     * Returns all requests.
     *
     * @return List of request entities.
     */

    public List<Request> findAllRequests() throws DBException, SQLException {
        List<Request> requestList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_FIND_ALL_REQUESTS);
            while (rs.next()) {
                requestList.add(extractRequest(rs));
            }
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            LOG.error(Messages.ERR_CANNOT_GET_ALL_REQUESTS);
            throw new DBException(Messages.ERR_CANNOT_GET_ALL_REQUESTS, e);
        } finally {
            close(con, stmt, rs);
        }
        return requestList;
    }

    /**
     * Create request.
     *
     * @param request;
     */

    public void createRequest(Request request) throws DBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_CREATE_REQUEST, Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            pstmt.setInt(k++, request.getIdPilot());
            pstmt.setInt(k++, request.getIdNavigator());
            pstmt.setInt(k++, request.getIdSpark());
            pstmt.setInt(k, request.getIdSteward());
            pstmt.executeUpdate();
//            ResultSet rs = pstmt.getGeneratedKeys();
//            if (rs.next()) {
//                request.setId(rs.getInt(1));  можно удалить
//            }
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            LOG.error(Messages.ERR_CANNOT_CREATE_REQUEST);
            throw new DBException(Messages.ERR_CANNOT_CREATE_REQUEST, e);
        } finally {
            close(con);
            close(pstmt);
        }
    }

    /**
     * Find flight by flight id.
     *
     * @param id;
     */

    public Request findRequestById(int id) throws DBException, SQLException {
        Request request = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            statement = con.prepareStatement(SQL_FIND_REQUEST_BY_ID);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                request = extractRequest(rs);
            }
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            LOG.error(Messages.ERR_CANNOT_GET_REQUEST_BY_ID, e);
            throw new DBException(Messages.ERR_CANNOT_GET_REQUEST_BY_ID, e);
        } finally {
            close(con, statement, rs);
        }
        return request;
    }

    /**
     * Update request.
     *
     * @param request;
     */

    public void updateRequestStatusById(Request request) throws SQLException, DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_UPDATE_REQUEST_STATUS);
            int k = 1;
            pstmt.setInt(k++, request.getRequestStatusId());
            pstmt.setInt(k, request.getId());
            pstmt.executeUpdate();
            con.commit();
        } catch (SQLException e){
            con.rollback();
            LOG.error(Messages.ERR_CANNOT_UPDATE_REQUEST_STATUS);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_REQUEST_STATUS, e);
        } finally {
            close(con);
            close(pstmt);
        }
    }

    public List<Crew> findAllFreeCrew() throws DBException, SQLException {
        List<Crew> crewList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try{
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_FIND_ALL_FREE_CREW);
            while (rs.next()){
                crewList.add(extractCrew(rs));
            }
            con.commit();
        }catch (SQLException e){
            con.rollback();
            LOG.error("Cannot find free crew", e);
            throw new DBException("Cannot find free crew", e);
        } finally {
            close(con, stmt, rs);
        }
        return crewList;
    }

    /**
     * Extracts a user entity from the result set.
     *
     * @param rs Result set from which a user entity will be extracted.
     * @return User entity
     */
    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(Fields.ENTITY_ID));
        user.setLogin(rs.getString(Fields.USER_LOGIN));
        user.setPassword(rs.getString(Fields.USER_PASSWORD));
        user.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
        user.setLastName(rs.getString(Fields.USER_LAST_NAME));
        user.setUserRoleId(rs.getInt(Fields.USER_ROLE_ID));
        return user;
    }

    /**
     * Extracts a flight entity from the result set.
     *
     * @param rs Result set from which a Flight entity will be extracted.
     * @return Flight entity
     */

    private Flight extractFlight(ResultSet rs) throws SQLException {
        Flight flight = new Flight();
        flight.setId(rs.getInt(Fields.ENTITY_ID));
        flight.setFlightName(rs.getString(Fields.FLIGHT_NAME));
        flight.setWhence(rs.getString(Fields.FLIGHT_FROM_WHERE));
        flight.setWhereto(rs.getString(Fields.FLIGHT_WHERE_TO));
        flight.setDate(rs.getDate(Fields.FLIGHT_DATE));
        flight.setFlightStatusId(rs.getInt(Fields.FLIGHT_STATUS));
        flight.setCrewId(rs.getInt(Fields.CREW_ID_IN_FLIGHT));
        return flight;
    }

    /**
     * Extracts a staff entity from the result set.
     *
     * @param rs Result set from which a Staff entity will be extracted.
     * @return Staff entity
     */

    private Staff extractStaff(ResultSet rs) throws SQLException {
        Staff staff = new Staff();
        staff.setId(rs.getInt(Fields.ENTITY_ID));
        staff.setFirstName(rs.getString(Fields.STAFF_FIRST_NAME));
        staff.setLastName(rs.getString(Fields.STAFF_LAST_NAME));
        staff.setDepartamenId(rs.getInt(Fields.STAFF_DEPARTAMENT_ID));
        staff.setCrewId(rs.getInt(Fields.STAFF_CREW_ID));
        return staff;
    }

    /**
     * Extracts a crew entity from the result set.
     *
     * @param rs Result set from which a Crew entity will be extracted.
     * @return Crew entity
     */

    private Crew extractCrew(ResultSet rs) throws SQLException {
        Crew crew = new Crew();
        crew.setId(rs.getInt(Fields.ENTITY_ID));
        crew.setCrewStatusId(rs.getInt(Fields.CREW_STATUS));
        return crew;
    }

    /**
     * Extracts a request entity from the result set.
     *
     * @param rs Result set from which a Request entity will be extracted.
     * @return Request entity
     */

    private Request extractRequest(ResultSet rs) throws SQLException {
        Request request = new Request();
        request.setId(rs.getInt(Fields.ENTITY_ID));
        request.setIdPilot(rs.getInt(Fields.REQUEST_PILOT_ID));
        request.setIdNavigator(rs.getInt(Fields.REQUEST_NAVIGATOR_ID));
        request.setIdSpark(rs.getInt(Fields.REQUEST_SPARK_ID));
        request.setIdSteward(rs.getInt(Fields.REQUEST_STEWARD_ID));
        request.setRequestStatusId(rs.getInt(Fields.REQUEST_STATUS));
        return request;
    }

    /**
     * Closes a connection.
     *
     * @param con Connection to be closed.
     */
    private void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
            }
        }
    }

    /**
     * Closes a statement object.
     */
    private void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_STATEMENT, ex);
            }
        }
    }

    /**
     * Closes a result set object.
     */
    private void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_RESULTSET, ex);
            }
        }
    }

    /**
     * Closes resources.
     */
    private void close(Connection con, Statement stmt, ResultSet rs) {
        close(rs);
        close(stmt);
        close(con);
    }
}
