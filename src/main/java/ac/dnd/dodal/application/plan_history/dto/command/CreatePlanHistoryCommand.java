package ac.dnd.dodal.application.plan_history.dto.command;

import ac.dnd.dodal.domain.plan_history.model.PlanHistory;

public record CreatePlanHistoryCommand(
    Long userId,
    Long goalId) {

    public static PlanHistory toPlanHistory(CreatePlanHistoryCommand command) {
        return new PlanHistory();
    }
}
