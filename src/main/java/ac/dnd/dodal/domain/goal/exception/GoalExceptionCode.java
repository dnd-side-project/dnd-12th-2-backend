package ac.dnd.dodal.domain.goal.exception;

import ac.dnd.dodal.common.enums.ResultCode;
import ac.dnd.dodal.domain.goal.constraint.GoalConstraints;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum GoalExceptionCode implements ResultCode {

    GOAL_TITLE_EXCEED_MAX_LENGTH("GOA_001",
            "Goal title is too long: Max length is " + GoalConstraints.MAX_GOAL_TITLE_LENGTH),
    GOAL_TITLE_EMPTY("GOA_002", "Goal title is empty"),
    DELETED_GOAL("GOA_003", "Wrong Access: Deleted goal"),
    GOAL_NOT_FOUND("GOA_004", "Goal not found"),
    GOAL_ALREADY_ACHIEVED("GOA_005", "Goal already achieved"),
    GOAL_ALREADY_DELETED("GOA_006", "Goal already deleted"),
    ACHIEVED_GOAL("GOA_007", "Wrong Access: Achieved goal"),
    ;

    private final String code;
    private final String message;
}
