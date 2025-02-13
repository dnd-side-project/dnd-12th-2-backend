package ac.dnd.dodal.application.plan.dto.command;

import ac.dnd.dodal.domain.plan.enums.PlanStatus;

public record CompletePlanCommand(
    Long planId,
    PlanStatus status,
    String question,
    String indicator
) {
    
}
