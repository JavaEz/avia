package ua.nure.moisieiev.summaryTask4.entity;

public class Request extends Entity {
    private int idPilot;
    private int idNavigator;
    private int idSpark;
    private int idSteward;
    private int requestStatusId;

    public int getIdPilot() {
        return idPilot;
    }

    public void setIdPilot(int idPilot) {
        this.idPilot = idPilot;
    }

    public int getIdNavigator() {
        return idNavigator;
    }

    public void setIdNavigator(int idNavigator) {
        this.idNavigator = idNavigator;
    }

    public int getIdSpark() {
        return idSpark;
    }

    public void setIdSpark(int idSpark) {
        this.idSpark = idSpark;
    }

    public int getIdSteward() {
        return idSteward;
    }

    public void setIdSteward(int idSteward) {
        this.idSteward = idSteward;
    }

    public int getRequestStatusId() {
        return requestStatusId;
    }

    public void setRequestStatusId(int requestStatusId) {
        this.requestStatusId = requestStatusId;
    }

    @Override
    public String toString() {
        return "Request{" +
                "idPilot=" + idPilot +
                ", idNavigator=" + idNavigator +
                ", idSpark=" + idSpark +
                ", idSteward=" + idSteward +
                ", requestStatusId=" + requestStatusId +
                '}';
    }
}
