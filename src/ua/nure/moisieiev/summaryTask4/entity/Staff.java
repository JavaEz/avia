package ua.nure.moisieiev.summaryTask4.entity;
/**
 * Staff entity.
 *
 * @author S.Moisieiev
 *
 */
public class Staff extends Entity {
    private String firstName;
    private String lastName;
    private int departamenId;
    private int crewId;

    public int getCrewId() {
        return crewId;
    }

    public void setCrewId(int crewId) {
        this.crewId = crewId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getDepartamenId() {
        return departamenId;
    }

    public void setDepartamenId(int departamenId) {
        this.departamenId = departamenId;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", departamenId=" + departamenId +
                ", crewId=" + crewId +
                '}';
    }
}
