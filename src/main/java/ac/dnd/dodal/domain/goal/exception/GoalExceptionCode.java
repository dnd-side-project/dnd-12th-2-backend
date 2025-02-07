package ac.dnd.dodal.domain.goal.exception;

import ac.dnd.dodal.common.enums.ResultCode;
import ac.dnd.dodal.domain.goal.constraint.GoalConstraint;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum GoalExceptionCode implements ResultCode {

    GOAL_TITLE_EXCEED_MAX_LENGTH("GOAL_001",
            "Goal title is too long: Max length is " + GoalConstraint.MAX_GOAL_TITLE_LENGTH),
    GOAL_TITLE_EMPTY("GOAL_002", "Goal title is empty"),
    DELETED_GOAL("GOAL_003", "Wrong Access: Deleted goal"),
    GOAL_NOT_FOUND("GOAL_004", "Goal not found"),
    GOAL_ALREADY_ACHIEVED("GOAL_005", "Goal already achieved"),
    GOAL_ALREADY_DELETED("GOAL_006", "Goal already deleted"),
    ;

    private final String code;
    private final String message;
}
