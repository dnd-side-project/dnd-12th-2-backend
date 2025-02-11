package ac.dnd.dodal.common.exception;

import ac.dnd.dodal.common.enums.CommonResultCode;
import ac.dnd.dodal.common.enums.ResultCode;

public class BadRequestException extends DodalException {

    public BadRequestException() {
        super(CommonResultCode.BAD_REQUEST);
    }

    public BadRequestException(ResultCode resultCode) {
        super(resultCode);
    }

    public BadRequestException(ResultCode resultCode, String message) {
        super(resultCode, message);
    }

    public BadRequestException(ResultCode resultCode, Throwable cause) {
        super(resultCode, cause);
    }

    public BadRequestException(ResultCode resultCode, String message, Throwable cause) {
        super(resultCode, message, cause);
    }
}
