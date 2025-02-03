package ac.dnd.dodal.common.exception;

import ac.dnd.dodal.common.enums.CommonResultCode;
import ac.dnd.dodal.common.enums.ResultCode;

public class InternalServerError extends DodalException {

    public InternalServerError() {
        super(CommonResultCode.INTERNAL_SERVER_ERROR);
    }

    public InternalServerError(ResultCode resultCode) {
        super(resultCode);
    }

    public InternalServerError(ResultCode resultCode, String message) {
        super(resultCode, message);
    }

    public InternalServerError(ResultCode resultCode, Throwable cause) {
        super(resultCode, cause);
    }

    public InternalServerError(ResultCode resultCode, String message, Throwable cause) {
        super(resultCode, message, cause);
    }
}
