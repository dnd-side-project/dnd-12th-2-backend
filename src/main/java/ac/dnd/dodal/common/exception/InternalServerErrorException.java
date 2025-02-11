package ac.dnd.dodal.common.exception;

import ac.dnd.dodal.common.enums.CommonResultCode;
import ac.dnd.dodal.common.enums.ResultCode;

public class InternalServerErrorException extends DodalException {

    public InternalServerErrorException() {
        super(CommonResultCode.INTERNAL_SERVER_ERROR);
    }

    public InternalServerErrorException(ResultCode resultCode) {
        super(resultCode);
    }

    public InternalServerErrorException(ResultCode resultCode, String message) {
        super(resultCode, message);
    }

    public InternalServerErrorException(ResultCode resultCode, Throwable cause) {
        super(resultCode, cause);
    }

    public InternalServerErrorException(ResultCode resultCode, String message, Throwable cause) {
        super(resultCode, message, cause);
    }
}
