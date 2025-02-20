package ac.dnd.dodal.application.goal.dto.command;

import java.time.LocalDateTime;

public record CreateGoalAndPlanCommand(
    Long userId,
    String goalTitle,
    String planTitle,
    LocalDateTime startDate,
    LocalDateTime endDate
) {
}
