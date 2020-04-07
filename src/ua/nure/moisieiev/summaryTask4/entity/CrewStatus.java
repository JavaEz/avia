package ua.nure.moisieiev.summaryTask4.entity;

public enum CrewStatus {
    NOT_READY, READY;

    public static CrewStatus getCrewStatus(Crew crew) {
        int crewStatus = crew.getCrewStatusId();
        return CrewStatus.values()[crewStatus];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
