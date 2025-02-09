package ac.dnd.dodal.application.goal.usecase;

import ac.dnd.dodal.application.goal.dto.command.CreateGoalCommand;

public interface CreateGoalUseCase {

    Long create(CreateGoalCommand command);
}
