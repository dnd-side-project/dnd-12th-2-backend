package ac.dnd.dodal.common.exception;

import ac.dnd.dodal.common.enums.ResultCode;
import lombok.Getter;

@Getter
public class DodalException extends RuntimeException {

    private final ResultCode resultCode;

    public DodalException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    public DodalException(ResultCode resultCode, String message) {
        super(message);
        this.resultCode = resultCode;
    }

    public DodalException(ResultCode resultCode, Throwable cause) {
        super(resultCode.getMessage(), cause);
        this.resultCode = resultCode;
    }

    public DodalException(ResultCode resultCode, String message, Throwable cause) {
        super(message, cause);
        this.resultCode = resultCode;
    }
}
