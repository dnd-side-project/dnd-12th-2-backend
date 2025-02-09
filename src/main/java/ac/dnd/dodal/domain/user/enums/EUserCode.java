package ac.dnd.dodal.domain.user.enums;

import ac.dnd.dodal.common.enums.ResultCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EUserCode implements ResultCode {

    //failure
    NO_SUCH_ROLE("USER001", "No such user role"),
    NOT_FOUND_USER("USER002", "User not found"),
    ACCESS_DENIED("USER003", "User access denied"),
    ;

    private final String code;
    private final String message;
}
