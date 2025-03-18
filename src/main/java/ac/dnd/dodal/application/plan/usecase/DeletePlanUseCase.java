package ac.dnd.dodal.application.plan.usecase;

import ac.dnd.dodal.application.plan.dto.command.DeletePlanCommand;

public interface DeletePlanUseCase {

    void delete(DeletePlanCommand command);
}

