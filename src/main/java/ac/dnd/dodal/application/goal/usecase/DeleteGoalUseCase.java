package ac.dnd.dodal.application.goal.usecase;

import ac.dnd.dodal.application.goal.dto.command.DeleteGoalCommand;

public interface DeleteGoalUseCase {

    void delete(DeleteGoalCommand command);
}
