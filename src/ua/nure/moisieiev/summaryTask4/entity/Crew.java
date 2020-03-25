package ua.nure.moisieiev.summaryTask4.entity;
/**
 * Crew entity.
 *
 * @author S.Moisieiev
 *
 */
public class Crew  extends Entity{
    private String crewName;

    public String getCrewName() {
        return crewName;
    }

    public void setCrewName(String crewName) {
        this.crewName = crewName;
    }

    @Override
    public String toString() {
        return "Crew{" +
                "crewName='" + crewName + '\'' +
                '}';
    }
}
