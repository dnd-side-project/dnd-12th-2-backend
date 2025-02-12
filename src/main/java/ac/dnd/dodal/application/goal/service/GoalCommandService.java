package ac.dnd.dodal.application.goal.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ac.dnd.dodal.domain.goal.model.Goal;
import ac.dnd.dodal.application.goal.dto.command.*;
import ac.dnd.dodal.application.goal.usecase.CreateGoalUseCase;
import ac.dnd.dodal.application.goal.usecase.AchieveGoalUseCase;
import ac.dnd.dodal.application.goal.usecase.DeleteGoalUseCase;

@Service
@Transactional
@RequiredArgsConstructor
public class GoalCommandService
        implements CreateGoalUseCase, AchieveGoalUseCase, DeleteGoalUseCase {

    private final GoalService goalService;

    @Override
    public Long create(CreateGoalCommand command) {
        Goal goal = CreateGoalCommand.toEntity(command);

        return goalService.save(goal).getGoalId();
    }

    @Override
    public void achieve(AchieveGoalCommand command) {
        Goal goal = goalService.findByIdOrThrow(command.goalId());
        goal.achieve(command.userId());

        goalService.save(goal);
    }

    @Override
    public void delete(DeleteGoalCommand command) {
        Goal goal = goalService.findByIdOrThrow(command.goalId());
        goal.delete(command.userId());

        goalService.save(goal);
    }
}
