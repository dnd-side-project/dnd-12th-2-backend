package ac.dnd.dodal.ui.plan.request;

import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.util.Set;

import ac.dnd.dodal.application.plan.dto.command.AddSamePlanCommand;

public record AddSamePlanRequest(
    LocalDateTime startDate,
    LocalDateTime endDate,
    Set<DayOfWeek> iterationDays,
    int iterationCount
) {

    public AddSamePlanCommand toAddSamePlanCommand(
            Long userId, Long goalId, Long planHistoryId, Long planId) {
        return new AddSamePlanCommand(userId, goalId, planHistoryId, planId, startDate, endDate,
                iterationDays, iterationCount);
    }

    public AddSamePlanRequest(LocalDateTime startDate, LocalDateTime endDate,
            Set<DayOfWeek> iterationDays, int iterationCount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.iterationDays = iterationDays;
        this.iterationCount = iterationCount;
    }
}
