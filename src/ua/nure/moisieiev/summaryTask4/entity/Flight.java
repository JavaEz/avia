package ua.nure.moisieiev.summaryTask4.entity;
/**
 * Flight entity.
 *
 * @author S.Moisieiev
 *
 */
import java.sql.Date;

public class Flight extends Entity {
    private String flightName;
    private String whence;
    private String whereto;
    private Date date;
    private int flightStatusId;
    private int crewId;

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getWhence() {
        return whence;
    }

    public void setWhence(String whence) {
        this.whence = whence;
    }

    public String getWhereto() {
        return whereto;
    }

    public void setWhereto(String whereto) {
        this.whereto = whereto;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getFlightStatusId() {
        return flightStatusId;
    }

    public void setFlightStatusId(int flightStatusId) {
        this.flightStatusId = flightStatusId;
    }

    public int getCrewId() {
        return crewId;
    }

    public void setCrewId(int crewId) {
        this.crewId = crewId;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightName='" + flightName + '\'' +
                ", whence='" + whence + '\'' +
                ", whereto='" + whereto + '\'' +
                ", date=" + date +
                ", flightStatusId=" + flightStatusId +
                ", crewId=" + crewId +
                '}';
    }
}
