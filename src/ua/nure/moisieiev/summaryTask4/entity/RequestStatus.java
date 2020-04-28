package ua.nure.moisieiev.summaryTask4.entity;
/**
 * Request status entity.
 *
 * @author S.Moisieiev
 *
 */
public enum RequestStatus {
    COMPLETED, CANCELED, PENDING;

    public static RequestStatus getRequestStatus(Request request){
        int requestStatus = request.getRequestStatusId();
        return RequestStatus.values()[requestStatus];
    }

    public String getName() {
        return name().toLowerCase();
    }

}
