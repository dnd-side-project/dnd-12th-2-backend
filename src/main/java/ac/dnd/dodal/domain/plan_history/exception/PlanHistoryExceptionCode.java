package ac.dnd.dodal.domain.plan_history.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import ac.dnd.dodal.common.enums.ResultCode;

@Getter
@RequiredArgsConstructor
public enum PlanHistoryExceptionCode implements ResultCode {

    PLAN_HISTORY_NOT_FOUND("PLH001", "Plan history not found"),
    PLAN_HISTORY_ALREADY_DELETED("PLH002", "Plan history already deleted"),
    ;

    private final String code;
    private final String message;
}
