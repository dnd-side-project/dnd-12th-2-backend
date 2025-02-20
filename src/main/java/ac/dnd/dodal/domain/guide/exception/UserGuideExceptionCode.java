package ac.dnd.dodal.domain.guide.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import ac.dnd.dodal.common.enums.ResultCode;

@RequiredArgsConstructor
@Getter
public enum UserGuideExceptionCode implements ResultCode {

    USER_GUIDE_NOT_FOUND("USG001", "UserGuide not found"),
    FAIL_TO_GENERATE_USER_GUIDE("USG002", "Fail to generate user guide"),
    ;

    private final String code;
    private final String message;
}
