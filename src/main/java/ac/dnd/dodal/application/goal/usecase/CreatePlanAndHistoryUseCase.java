package ac.dnd.dodal.application.goal.usecase;

import ac.dnd.dodal.application.plan.dto.command.CreatePlanAndHistoryCommand;

public interface CreatePlanAndHistoryUseCase {

    Long createPlanAndHistory(CreatePlanAndHistoryCommand command);
}
