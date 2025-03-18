package ac.dnd.dodal.application.goal.usecase;

import ac.dnd.dodal.application.goal.dto.command.UpdateGoalCommand;

public interface UpdateGoalUseCase {
    void updateTitle(UpdateGoalCommand command);
}
