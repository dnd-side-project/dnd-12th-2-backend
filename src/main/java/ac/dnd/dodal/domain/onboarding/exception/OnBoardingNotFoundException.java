package ac.dnd.dodal.domain.onboarding.exception;

import ac.dnd.dodal.common.enums.ResultCode;
import ac.dnd.dodal.common.exception.NotFoundException;

public class OnBoardingNotFoundException extends NotFoundException {
    public OnBoardingNotFoundException() {
        super(OnBoardingExceptionCode.NOT_FOUND_ANSWER);
    }

    public OnBoardingNotFoundException(ResultCode resultCode) {
        super(resultCode);
    }

    public OnBoardingNotFoundException(ResultCode resultCode, String message) {
        super(resultCode, message);
    }

    public OnBoardingNotFoundException(ResultCode resultCode, Throwable cause) {
        super(resultCode, cause);
    }

    protected OnBoardingNotFoundException(ResultCode resultCode, String message, Throwable cause) {
        super(resultCode, message, cause);
    }
}
