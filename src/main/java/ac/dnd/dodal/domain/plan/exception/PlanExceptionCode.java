package ac.dnd.dodal.domain.plan.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


import ac.dnd.dodal.common.enums.ResultCode;
import ac.dnd.dodal.domain.plan.constraint.PlanConstraints;

@Getter
@RequiredArgsConstructor
public enum PlanExceptionCode implements ResultCode {

    PLAN_NOT_FOUND("PLN_001", "Plan not found"),
    PLAN_TITLE_EMPTY("PLN_002", "Plan title is empty"),
    PLAN_TITLE_EXCEED_MAX_LENGTH("PLN_003",
            "Plan title is too long: Max length is " + PlanConstraints.MAX_PLAN_TITLE_LENGTH),
    PLAN_START_DATE_AFTER_END_DATE("PLN_004", "Plan start date is after end date"),
    PLAN_SUCCEED_AFTER_START_DATE("PLN_005", "Plan succeed date is after start date"),
    PLAN_ALREADY_SUCCEED("PLN_006", "Plan already succeed"),
    PLAN_ALREADY_DELETED("PLN_007", "Plan already deleted"),
    ;

    private final String code;
    private final String message;
}
