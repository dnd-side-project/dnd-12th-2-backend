package ac.dnd.dodal.application.plan.usecase;

import ac.dnd.dodal.application.plan.dto.command.AddSamePlanCommand;
import ac.dnd.dodal.application.plan.dto.command.AddNewPlanCommand;

public interface AddPlanUseCase {

    void addSamePlan(AddSamePlanCommand command);

    void addNewPlan(AddNewPlanCommand command);
}
