package ac.dnd.dodal.domain.user.exception;

import ac.dnd.dodal.common.enums.ResultCode;
import ac.dnd.dodal.common.exception.DodalException;
import ac.dnd.dodal.domain.user.enums.E_user_code;


public class UserException extends DodalException {

    public UserException() {
        super(E_user_code.NOT_FOUND_USER);
    }

    public UserException(ResultCode resultCode) {
        super(resultCode);
    }

    public UserException(ResultCode resultCode, String message) {
        super(resultCode, message);
    }

    public UserException(ResultCode resultCode, Throwable cause) {
        super(resultCode, cause);
    }

    protected UserException(ResultCode resultCode, String message, Throwable cause) {
        super(resultCode, message, cause);
    }

}
