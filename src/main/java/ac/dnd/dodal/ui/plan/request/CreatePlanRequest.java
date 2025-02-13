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

    public CreatePlanRequest(String title, LocalDateTime startDate, LocalDateTime endDate) {
        if (title == null) {
            throw new IllegalArgumentException("Title is required");
        }
        if (startDate == null) {
            throw new IllegalArgumentException("Start date is required");
        }
        if (endDate == null) {
            throw new IllegalArgumentException("End date is required");
        }
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
