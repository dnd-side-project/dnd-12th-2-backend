package ac.dnd.dodal.common.exception;

import ac.dnd.dodal.common.enums.CommonResultCode;
import ac.dnd.dodal.common.enums.ResultCode;

public class UnauthorizedException extends DodalException {

    public UnauthorizedException() {
        super(CommonResultCode.UNAUTHORIZED);
    }

    public UnauthorizedException(ResultCode resultCode) {
        super(resultCode);
    }

    public UnauthorizedException(ResultCode resultCode, String message) {
        super(resultCode, message);
    }

    public UnauthorizedException(ResultCode resultCode, Throwable cause) {
        super(resultCode, cause);
    }

    public UnauthorizedException(ResultCode resultCode, String message, Throwable cause) {
        super(resultCode, message, cause);
    }
}
