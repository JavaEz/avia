package ua.nure.moisieiev.summaryTask4.entity;

public enum Crew {
    READY,NOT_READY;

    public static Crew getFlightStatus(Flight flight) {
        int crew = flight.getCrewId();
        return Crew.values()[crew];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
