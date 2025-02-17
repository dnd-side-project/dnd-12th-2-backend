package ac.dnd.dodal.domain.plan.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


import ac.dnd.dodal.common.enums.ResultCode;
import ac.dnd.dodal.domain.plan.constraint.PlanConstraints;

@Getter
@RequiredArgsConstructor
public enum PlanExceptionCode implements ResultCode {

    PLAN_NOT_FOUND("PLN001", "Plan not found"),
    PLAN_TITLE_EMPTY("PLN002", "Plan title is empty"),
    PLAN_TITLE_EXCEED_MAX_LENGTH("PLN003",
            "Plan title is too long: Max length is " + PlanConstraints.MAX_PLAN_TITLE_LENGTH),
    PLAN_START_DATE_AFTER_END_DATE("PLN004", "Plan start date is after end date"),
    PLAN_SUCCEED_AFTER_START_DATE("PLN005", "Plan succeed date is after start date"),
    PLAN_ALREADY_COMPLETED("PLN006", "Plan already completed"),
    PLAN_ALREADY_DELETED("PLN007", "Plan already deleted"),
    REQUIRED_FEEDBACK("PLN008", "Feedback is required"),
    INVALID_PLAN_STATUS("PLN009", "Invalid plan status: Only success, failure, none are allowed"),
    ;

    private final String code;
    private final String message;
}
