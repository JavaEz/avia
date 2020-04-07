package ua.nure.moisieiev.summaryTask4.entity;

public class Crew extends Entity{
    private int crewStatusId;

    public int getCrewStatusId() {
        return crewStatusId;
    }

    public void setCrewStatusId(int crewStatusId) {
        this.crewStatusId = crewStatusId;
    }

    @Override
    public String toString() {
        return "Crew{" +
                "crewStatusId=" + crewStatusId +
                '}';
    }
}
