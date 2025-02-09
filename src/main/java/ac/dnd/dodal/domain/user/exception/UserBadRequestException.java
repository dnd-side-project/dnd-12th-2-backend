package ac.dnd.dodal.domain.user.exception;

import ac.dnd.dodal.common.enums.ResultCode;
import ac.dnd.dodal.common.exception.BadRequestException;
import ac.dnd.dodal.domain.user.enums.EUserCode;

public class UserBadRequestException extends BadRequestException {

    public UserBadRequestException() {
        super(EUserCode.NO_SUCH_ROLE);
    }

    public UserBadRequestException(ResultCode resultCode) {
        super(resultCode);
    }

    public UserBadRequestException(ResultCode resultCode, String message) {
        super(resultCode, message);
    }

    public UserBadRequestException(ResultCode resultCode, Throwable cause) {
        super(resultCode, cause);
    }

    protected UserBadRequestException(ResultCode resultCode, String message, Throwable cause) {
        super(resultCode, message, cause);
    }
}
