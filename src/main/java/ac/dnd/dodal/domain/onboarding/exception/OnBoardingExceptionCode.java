package ac.dnd.dodal.domain.onboarding.exception;

import ac.dnd.dodal.common.enums.ResultCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OnBoardingExceptionCode implements ResultCode {

    NOT_FOUND_ANSWER("ONB001", "Answer not found"),
    INVALID_ANSWER_COUNT("ONB002", "The number of answers does not match the total number of questions."),
    ALREADY_ANSWERED("ONB003", "The user has already answered."),
    NOT_FOUND_Question("ONB004", "Question not found"),
    ;

    private final String code;
    private final String message;

}
