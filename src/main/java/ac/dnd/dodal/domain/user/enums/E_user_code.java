package ac.dnd.dodal.domain.user.enums;

import ac.dnd.dodal.common.enums.ResultCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum E_user_code implements ResultCode {

    //failure
    NO_SUCH_ROLE("USER001", "No such user role"),
    NOT_FOUND_USER("USER002", "User not found"),
    ACCESS_DENIED("USER003", "User access denied"),

    //success
    SUCCESS_LOGOUT("USER101", "User logout success"),;

    private final String code;
    private final String message;
}
