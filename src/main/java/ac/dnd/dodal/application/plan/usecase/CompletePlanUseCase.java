package ac.dnd.dodal.application.plan.usecase;

import ac.dnd.dodal.application.plan.dto.command.CompletePlanCommand;
import ac.dnd.dodal.domain.plan.model.Plan;
import ac.dnd.dodal.ui.plan.response.PlanElement;

import java.util.List;

public interface CompletePlanUseCase {

    List<PlanElement> completePlan(CompletePlanCommand command);
}
