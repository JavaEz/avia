package ua.nure.moisieiev.summaryTask4.entity;
/**
 * Flight status entity.
 *
 * @author S.Moisieiev
 *
 */
public enum FlightStatus {
    OPENED, ARRIVED, CANCELED, PREPARATION;

    public static FlightStatus getFlightStatus(Flight flight) {
        int flightStatus = flight.getFlightStatusId();
        return FlightStatus.values()[flightStatus];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
