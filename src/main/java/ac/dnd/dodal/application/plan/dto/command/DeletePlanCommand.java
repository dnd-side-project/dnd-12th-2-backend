package ac.dnd.dodal.application.plan.dto.command;

public record DeletePlanCommand(
    Long userId,
    Long planId
) {
}
