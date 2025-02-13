package ac.dnd.dodal.application.plan.usecase;

import ac.dnd.dodal.application.plan.dto.command.CompletePlanCommand;
import ac.dnd.dodal.domain.plan.model.Plan;

public interface CompletePlanUseCase {

    Plan completePlan(CompletePlanCommand command);
}
