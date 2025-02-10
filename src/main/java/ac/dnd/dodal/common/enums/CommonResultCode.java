package ac.dnd.dodal.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonResultCode implements ResultCode {

    // success
    SUCCESS("COM000", "Success"),

    // failure
    // common
    BAD_REQUEST("COM001", "Bad Request"),
    NOT_FOUND("COM002", "Not Found"),
    UNAUTHORIZED("COM003", "Unauthorized"),
    FORBIDDEN("COM004", "Forbidden"),
    INTERNAL_SERVER_ERROR("COM005", "Internal Server Error"),

    // domain
    AUTH_EXCEPTION("AUT000", "Auth Exception"),
    USER_EXCEPTION("USR000", "User Exception"),
    GOAL_EXCEPTION("GOA000", "Goal Exception"),
    PLAN_EXCEPTION("PLN000", "Plan Exception"),
    INDICATOR_EXCEPTION("IND000", "Indicator Exception"),
    NOTIFICATION_EXCEPTION("NOT000", "Notification Exception"),
    PLAN_HISTORY_EXCEPTION("PLH000", "Plan History Exception"),
    ;

    private final String code;
    private final String message;
}
