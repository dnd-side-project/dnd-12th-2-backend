package ac.dnd.dodal.application.plan.dto.command;

import java.time.LocalDateTime;

public record CreatePlanAndHistoryCommand(
    Long userId,
    Long goalId,
    String title,
    LocalDateTime startDate,
    LocalDateTime endDate
) {
}
