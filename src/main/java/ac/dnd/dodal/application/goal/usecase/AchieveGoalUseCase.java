package ac.dnd.dodal.application.goal.usecase;

import ac.dnd.dodal.application.goal.dto.command.AchieveGoalCommand;

public interface AchieveGoalUseCase {

    void achieve(AchieveGoalCommand command);
}
