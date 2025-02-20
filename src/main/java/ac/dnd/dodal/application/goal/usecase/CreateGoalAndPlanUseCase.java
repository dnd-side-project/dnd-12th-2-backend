package ac.dnd.dodal.application.goal.usecase;

import ac.dnd.dodal.application.goal.dto.command.CreateGoalAndPlanCommand;

public interface CreateGoalAndPlanUseCase {

    Long createGoalAndPlan(CreateGoalAndPlanCommand command);
}
