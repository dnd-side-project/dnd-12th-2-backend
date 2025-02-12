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
        return new AddNewPlanCommand(userId, goalId, planHistoryId, planId, title, startDate,
                endDate);
    }

    public AddNewPlanRequest(String title, LocalDateTime startDate, LocalDateTime endDate) {
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
