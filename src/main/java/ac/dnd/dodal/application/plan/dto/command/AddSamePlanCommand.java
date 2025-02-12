package ac.dnd.dodal.application.plan.dto.command;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Set;

public record AddSamePlanCommand(
    Long userId,
    Long goalId,
    Long planHistoryId,
    Long planId,
    LocalDateTime startDate,
    LocalDateTime endDate,
    Set<DayOfWeek> days,
    Integer iterationCount) {
}
