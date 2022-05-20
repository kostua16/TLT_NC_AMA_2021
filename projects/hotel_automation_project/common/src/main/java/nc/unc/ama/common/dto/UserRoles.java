package nc.unc.ama.common.dto;

import lombok.Getter;

@Getter
public enum UserRoles {

    GUEST(Constants.GUEST),
    ADMIN(Constants.ADMIN),
    STAFF(Constants.STAFF),
    API(Constants.API);

    private final String roleName;

    UserRoles(final String roleName) {
        this.roleName = roleName;
    }

    public static class Constants {
        public static final String GUEST = "GUEST";
        public static final String ADMIN = "ADMIN";
        public static final String STAFF = "STAFF";
        public static final String API = "API";
    }
}
