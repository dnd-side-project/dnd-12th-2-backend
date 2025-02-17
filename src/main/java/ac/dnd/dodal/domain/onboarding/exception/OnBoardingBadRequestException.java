package ac.dnd.dodal.domain.onboarding.exception;

import ac.dnd.dodal.common.enums.ResultCode;
import ac.dnd.dodal.common.exception.BadRequestException;

public class OnBoardingBadRequestException extends BadRequestException {

    public OnBoardingBadRequestException() {
        super(OnBoardingExceptionCode.INVALID_ANSWER_COUNT);
    }

    public OnBoardingBadRequestException(ResultCode resultCode) {
        super(resultCode);
    }

    public OnBoardingBadRequestException(ResultCode resultCode, String message) {
        super(resultCode, message);
    }

    public OnBoardingBadRequestException(ResultCode resultCode, Throwable cause) {
        super(resultCode, cause);
    }

    protected OnBoardingBadRequestException(ResultCode resultCode, String message, Throwable cause) {
        super(resultCode, message, cause);
    }
}
