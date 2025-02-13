package ac.dnd.dodal.application.plan.usecase;

import org.springframework.data.domain.Page;

import ac.dnd.dodal.domain.plan.model.Plan;
import ac.dnd.dodal.application.plan.dto.command.CompletePlanCommand;

public interface CompletePlanUseCase {

    Page<Plan> completePlan(CompletePlanCommand command);
}
