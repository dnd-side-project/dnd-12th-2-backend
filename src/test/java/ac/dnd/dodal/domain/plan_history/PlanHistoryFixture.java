package ac.dnd.dodal.domain.plan_history;

import java.time.LocalDateTime;

import ac.dnd.dodal.domain.plan_history.model.PlanHistory;
import ac.dnd.dodal.domain.goal.model.Goal;
import ac.dnd.dodal.domain.goal.GoalFixture;

public class PlanHistoryFixture {

    private static final Long HISTORY_ID = 1L;
    private static final Goal goal = GoalFixture.goal();

    public static PlanHistory planHistory() {
        return new PlanHistory(HISTORY_ID, goal,
                LocalDateTime.now(), LocalDateTime.now(), null);
    }

    public static PlanHistory deletedPlanHistory() {
        return new PlanHistory(HISTORY_ID, goal,
                LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
    }

    public static PlanHistory lastSuccessPlanHistory() {
        return new PlanHistory(HISTORY_ID, goal,
                LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
    }

    public static PlanHistory lastFailedPlanHistory() {
        return new PlanHistory(HISTORY_ID, goal,
                LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
    }
}
