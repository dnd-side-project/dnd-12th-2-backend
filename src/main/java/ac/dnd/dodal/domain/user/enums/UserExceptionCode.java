package ac.dnd.dodal.domain.user.enums;

import ac.dnd.dodal.common.enums.ResultCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserExceptionCode implements ResultCode {

    //failure
    NO_SUCH_ROLE("USR001", "No such user role"),
    NOT_FOUND_USER("USR002", "User not found"),
    DUPLICATED_NICKNAME("USR003", "Duplicated nickname"),
    DUPLICATED_EMAIL("USR004", "Duplicated email"),
    NO_SUCH_EMAIL("USR005", "No such email"),
    DELETED_USER("USR006", "Deleted user"),
    ;

    private final String code;
    private final String message;
}
