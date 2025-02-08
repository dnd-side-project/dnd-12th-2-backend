package ac.dnd.dodal.application.goal.service;

import org.springframework.stereotype.Service;

import ac.dnd.dodal.application.goal.dto.command.AchieveGoalCommand;
import ac.dnd.dodal.application.goal.dto.command.CreateGoalCommand;
import ac.dnd.dodal.application.goal.dto.command.DeleteGoalCommand;
import ac.dnd.dodal.application.goal.usecase.CreateGoalUseCase;
import ac.dnd.dodal.application.goal.usecase.AchieveGoalUseCase;
import ac.dnd.dodal.application.goal.usecase.DeleteGoalUseCase;

@Service
public class GoalCommandService
        implements CreateGoalUseCase, AchieveGoalUseCase, DeleteGoalUseCase {

    @Override
    public Long create(CreateGoalCommand command) {
        return null;
    }

    @Override
    public void achieve(AchieveGoalCommand command) {
    }

    @Override
    public void delete(DeleteGoalCommand command) {
    }
}
