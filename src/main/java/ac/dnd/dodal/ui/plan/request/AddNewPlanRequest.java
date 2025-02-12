package ac.dnd.dodal.ui.plan.request;

import java.time.LocalDateTime;

import ac.dnd.dodal.application.plan.dto.command.AddNewPlanCommand;

public record AddNewPlanRequest(
    String title,
    LocalDateTime startDate,
    LocalDateTime endDate
) {

    public AddNewPlanCommand toAddNewPlanCommand(
        Long userId, Long goalId, Long planHistoryId, Long planId) {
        return new AddNewPlanCommand(userId, goalId, planHistoryId, planId,
                title, startDate, endDate);
    }
}
