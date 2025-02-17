package ac.dnd.dodal.domain.goal.exception;

import ac.dnd.dodal.common.enums.ResultCode;
import ac.dnd.dodal.domain.goal.constraint.GoalConstraints;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum GoalExceptionCode implements ResultCode {

    GOAL_TITLE_EXCEED_MAX_LENGTH("GOA001",
            "Goal title is too long: Max length is " + GoalConstraints.MAX_GOAL_TITLE_LENGTH),
    GOAL_TITLE_EMPTY("GOA002", "Goal title is empty"),
    DELETED_GOAL("GOA003", "Wrong Access: Deleted goal"),
    GOAL_NOT_FOUND("GOA004", "Goal not found"),
    GOAL_ALREADY_ACHIEVED("GOA005", "Goal already achieved"),
    GOAL_ALREADY_DELETED("GOA006", "Goal already deleted"),
    ACHIEVED_GOAL("GOA007", "Wrong Access: Achieved goal"),
    GOAL_STATISTICS_NOT_FOUND("GOA008", "Goal statistics not found"),
    ;

    private final String code;
    private final String message;
}
