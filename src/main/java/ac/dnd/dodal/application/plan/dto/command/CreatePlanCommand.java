package ac.dnd.dodal.application.plan.dto.command;

import java.time.LocalDateTime;

import ac.dnd.dodal.domain.plan.model.Plan;

public record CreatePlanCommand(
        Long userId,
        Long goalId,
        Long planHistoryId,
        String title,
        LocalDateTime startDate,
        LocalDateTime endDate) {

    public static Plan toPlan(CreatePlanCommand command) {
        return new Plan(command.title(), command.startDate(), command.endDate());
    }
}
