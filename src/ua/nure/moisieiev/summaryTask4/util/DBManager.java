package ua.nure.moisieiev.summaryTask4.util;

import org.apache.log4j.Logger;
import ua.nure.moisieiev.summaryTask4.entity.Flight;
import ua.nure.moisieiev.summaryTask4.entity.User;
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
    private static final String SQL_UPDATE_FLIGHT_BY_ID = "UPDATE flights SET flight_name = ?, whence = ?,"+
            "whereto = ?, date = ?, flight_status = ?, crew_id = ? WHERE id = ?";

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
            LOG.error("CANNOT DELETE BY ID");
            throw new DBException("CANNOT DELETE BY ID", e);
        } finally {
            close(con);
            close(statement);
        }
    }

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
        } catch (SQLException e){
            con.rollback();
            LOG.error(Messages.ERR_CANNOT_GET_USER_BY_ID, e);
            throw new DBException(Messages.ERR_CANNOT_GET_USER_BY_ID, e);
        }finally {
            close(con, statement, rs);
        }
        return flight;
    }

    public void updateFlightById (Flight flight) throws SQLException, DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try{
            con = getConnection();
            pstmt = con.prepareStatement(SQL_UPDATE_FLIGHT_BY_ID);
            int k = 1;
            pstmt.setString(k++,flight.getFlightName());
            pstmt.setString(k++,flight.getWhence());
            pstmt.setString(k++, flight.getWhereto());
            pstmt.setDate(k++, flight.getDate());
            pstmt.setInt(k++, flight.getFlightStatusId());
            pstmt.setInt(k++, flight.getCrewId());
            pstmt.setInt(k++,flight.getId());
            pstmt.executeUpdate();
            con.commit();
        }catch (SQLException e){
            con.rollback();
            LOG.error("CANNOT UPDATE FLIGHT BY ID");
            throw new DBException("CANNOT UPDATE FLIGHT BY ID", e);
        }finally {
            close(con);
            close(pstmt);
        }
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
