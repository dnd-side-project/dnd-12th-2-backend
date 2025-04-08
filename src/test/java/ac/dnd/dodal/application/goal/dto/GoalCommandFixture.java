package ac.dnd.dodal.application.goal.dto;

import ac.dnd.dodal.application.goal.dto.command.CreateGoalCommand;
import ac.dnd.dodal.application.goal.dto.command.AchieveGoalCommand;
import ac.dnd.dodal.application.goal.dto.command.DeleteAllGoalCommand;
import ac.dnd.dodal.application.goal.dto.command.DeleteGoalCommand;

public class GoalCommandFixture {

    private static final Long USER_ID = 1L;
    private static final String TITLE = "title";
    private static final Long GOAL_ID = 1L;

    public static CreateGoalCommand createGoalCommand(Long userId, String title) {
        return new CreateGoalCommand(userId, title);
    }

    public static CreateGoalCommand createGoalCommand() {
        return createGoalCommand(USER_ID, TITLE);
    }

    public static CreateGoalCommand createGoalCommandWithBlankTitle() {
        return createGoalCommand(USER_ID, "       ");
    }

    public static CreateGoalCommand createGoalCommandWithEmptyTitle() {
        return createGoalCommand(USER_ID, "");
    }

    public static CreateGoalCommand createGoalCommandWithNullTitle() {
        return createGoalCommand(USER_ID, null);
    }

    public static CreateGoalCommand createGoalCommandExceedTitleLength() {
        return createGoalCommand(USER_ID, "a".repeat(21));
    }

    public static AchieveGoalCommand achieveGoalCommand(Long userId, Long goalId) {
        return new AchieveGoalCommand(userId, goalId);
    }

    public static AchieveGoalCommand achieveGoalCommand() {
        return achieveGoalCommand(USER_ID, GOAL_ID);
    }

    public static DeleteGoalCommand deleteGoalCommand(Long userId, Long goalId) {
        return new DeleteGoalCommand(userId, goalId);
    }

    public static DeleteGoalCommand deleteGoalCommand() {
        return deleteGoalCommand(USER_ID, GOAL_ID);
    }

    public static DeleteAllGoalCommand deleteAllGoalCommand() {
        return new DeleteAllGoalCommand(USER_ID);
    }
}
