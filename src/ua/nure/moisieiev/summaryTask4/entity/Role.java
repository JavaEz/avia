package ua.nure.moisieiev.summaryTask4.entity;
/**
 * Role entity.
 *
 * @author S.Moisieiev
 *
 */

public enum Role {
    ADMIN, DISPATCHER;


    public static Role getRole(User user) {
        int roleId = user.getUserRoleId();
        return Role.values()[roleId];
    }

    public String getName() {
        return name().toLowerCase();
    }
}


