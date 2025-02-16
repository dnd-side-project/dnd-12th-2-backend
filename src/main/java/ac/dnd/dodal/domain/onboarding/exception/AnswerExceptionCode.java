package ac.dnd.dodal.domain.onboarding.exception;

import ac.dnd.dodal.common.enums.ResultCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AnswerExceptionCode implements ResultCode {

    NOT_FOUND_ANSWER("ANS001", "Answer not found"),
    ;

    private final String code;
    private final String message;

    }
