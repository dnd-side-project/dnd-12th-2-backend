package ac.dnd.dodal.common.exception;

import ac.dnd.dodal.common.enums.CommonResultCode;
import ac.dnd.dodal.common.enums.ResultCode;

public class ForbiddenException extends DodalException {

    public ForbiddenException() {
        super(CommonResultCode.FORBIDDEN);
    }

    public ForbiddenException(ResultCode resultCode) {
        super(resultCode);
    }

    public ForbiddenException(ResultCode resultCode, String message) {
        super(resultCode, message);
    }

    public ForbiddenException(ResultCode resultCode, Throwable cause) {
        super(resultCode, cause);
    }

    public ForbiddenException(ResultCode resultCode, String message, Throwable cause) {
        super(resultCode, message, cause);
    }
}
