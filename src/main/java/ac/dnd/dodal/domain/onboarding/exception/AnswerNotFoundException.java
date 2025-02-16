package ac.dnd.dodal.domain.onboarding.exception;

import ac.dnd.dodal.common.enums.ResultCode;
import ac.dnd.dodal.common.exception.NotFoundException;

public class AnswerNotFoundException extends NotFoundException {
    public AnswerNotFoundException() {
        super(AnswerExceptionCode.NOT_FOUND_ANSWER);
    }

    public AnswerNotFoundException(ResultCode resultCode) {
        super(resultCode);
    }

    public AnswerNotFoundException(ResultCode resultCode, String message) {
        super(resultCode, message);
    }

    public AnswerNotFoundException(ResultCode resultCode, Throwable cause) {
        super(resultCode, cause);
    }

    protected AnswerNotFoundException(ResultCode resultCode, String message, Throwable cause) {
        super(resultCode, message, cause);
    }
}
