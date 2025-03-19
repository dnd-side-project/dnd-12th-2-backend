package ac.dnd.dodal.application.plan.dto.command;

import java.time.LocalDateTime;

public record AddNewPlanCommand(
        Long userId,
        Long goalId,
        Long planId,
        String title,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
