package ac.dnd.dodal.application.plan.usecase;

import ac.dnd.dodal.application.plan.dto.command.CreatePlanAndHistoryCommand;

public interface CreatePlanAndHistoryUseCase {

    void createPlanAndHistory(CreatePlanAndHistoryCommand command);
}
