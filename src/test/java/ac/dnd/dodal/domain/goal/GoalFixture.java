package ac.dnd.dodal.domain.goal;

import java.time.LocalDateTime;

import ac.dnd.dodal.domain.goal.model.Goal;

public class GoalFixture {

    private static final Long USER_ID = 1L;
    private static final Long GOAL_ID = 1L;
    private static final String TITLE = "title";

    public static Goal goal() {
        return new Goal(GOAL_ID, USER_ID, TITLE, false,
                LocalDateTime.now(), LocalDateTime.now(), null);
    }

    public static Goal achievedGoal() {
        return new Goal(GOAL_ID, USER_ID, TITLE, true,
                LocalDateTime.now(), LocalDateTime.now(), null);
    }

    public static Goal deletedGoal() {
        return new Goal(GOAL_ID, USER_ID, TITLE, false,
                LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
    }
}
