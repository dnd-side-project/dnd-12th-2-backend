package ac.dnd.dodal.domain.user.exception;

import ac.dnd.dodal.common.enums.ResultCode;
import ac.dnd.dodal.common.exception.NotFoundException;
import ac.dnd.dodal.domain.user.enums.UserExceptionCode;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException() {
        super(UserExceptionCode.NOT_FOUND_USER);
    }

    public UserNotFoundException(ResultCode resultCode) {
        super(resultCode);
    }

    public UserNotFoundException(ResultCode resultCode, String message) {
        super(resultCode, message);
    }

    public UserNotFoundException(ResultCode resultCode, Throwable cause) {
        super(resultCode, cause);
    }

    protected UserNotFoundException(ResultCode resultCode, String message, Throwable cause) {
        super(resultCode, message, cause);
    }
}
