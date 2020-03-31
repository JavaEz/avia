package ua.nure.moisieiev.summaryTask4.web.command.staffCommands;

import org.apache.log4j.Logger;
import ua.nure.moisieiev.summaryTask4.Path;
import ua.nure.moisieiev.summaryTask4.entity.Staff;
import ua.nure.moisieiev.summaryTask4.exception.AppException;
import ua.nure.moisieiev.summaryTask4.util.DBManager;
import ua.nure.moisieiev.summaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class StaffSaveCommand extends Command {

    private static final Logger LOG = Logger.getLogger(StaffSaveCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws  AppException {
        LOG.debug("Command starts");
        String firstName = request.getParameter("firstName");
        LOG.trace("Request parameter: firstName --> " + firstName);
        String lastName = request.getParameter("lastName");
        LOG.trace("Request parameter: lastName --> " + lastName);
        String departamenId = request.getParameter("departamenId");
        LOG.trace("Request parameter: departamenId --> " + departamenId);

        DBManager dbManager = DBManager.getInstance();

        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("id_staff"));
        } catch (NumberFormatException e) {
            LOG.error("MISTAKE! ID not a number");
        }
        Staff staff;
        if(id !=null){
            try {
                staff = dbManager.findStaffById(id);
                staff.setFirstName(firstName);
                staff.setLastName(lastName);
                staff.setDepartamenId(Integer.parseInt(departamenId));

                if(staff.getFirstName() != null && staff.getLastName() !=null
                    && staff.getDepartamenId() != 0){
                    try {
                        dbManager.updateStaffById(staff);
                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            if(firstName == null || firstName.isEmpty() || lastName == null
            || lastName.isEmpty() || departamenId == null || departamenId.isEmpty()){
                LOG.error("MISTAKE");
                return Path.PAGE_ERROR_PAGE;
            }else{
                try{
                    Staff staff1 = new Staff();
                    staff1.setFirstName(firstName);
                    staff1.setLastName(lastName);
                    staff1.setDepartamenId(Integer.parseInt(departamenId));
                    dbManager.createStaff(staff1);
                }catch (SQLException e){
                    LOG.error("CANNOT CREATE A NEW STAFF MEMBER");
                }
            }
        }
        LOG.debug("Command finished");
        return Path.COMMAND_STAFF_LIST;
    }
}
