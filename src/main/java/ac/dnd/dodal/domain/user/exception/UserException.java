package ac.dnd.dodal.domain.user.exception;

import ac.dnd.dodal.common.enums.ResultCode;
import ac.dnd.dodal.common.exception.NotFoundException;
import ac.dnd.dodal.domain.user.enums.EUserCode;


public class UserException extends NotFoundException {

    public UserException() {
        super(EUserCode.NOT_FOUND_USER);
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
