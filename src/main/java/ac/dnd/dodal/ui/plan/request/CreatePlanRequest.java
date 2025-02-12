package ac.dnd.dodal.ui.plan.request;

import java.time.LocalDateTime;

import ac.dnd.dodal.application.plan.dto.command.CreatePlanAndHistoryCommand;

public record CreatePlanRequest(
    String title,
    LocalDateTime startDate,
    LocalDateTime endDate
) {

    public CreatePlanAndHistoryCommand toCreatePlanAndHistoryCommand(Long userId, Long goalId) {
        return new CreatePlanAndHistoryCommand(userId, goalId, title, startDate, endDate);
    }
}
