package ua.nure.moisieiev.summaryTask4.entity;
/**
 * Department entity.
 *
 * @author S.Moisieiev
 *
 */
public enum Departament {
    PILOT, NAVIGATOR, SPARK, STEWARD;

    public static Departament getFlightStatus(Staff staff) {
        int depart = staff.getDepartamenId();
        return Departament.values()[depart];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
