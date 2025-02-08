package ac.dnd.dodal.application.goal.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import ac.dnd.dodal.common.exception.NotFoundException;
import ac.dnd.dodal.domain.goal.model.Goal;
import ac.dnd.dodal.domain.goal.exception.GoalExceptionCode;
import ac.dnd.dodal.application.goal.dto.command.AchieveGoalCommand;
import ac.dnd.dodal.application.goal.dto.command.CreateGoalCommand;
import ac.dnd.dodal.application.goal.dto.command.DeleteGoalCommand;
import ac.dnd.dodal.application.goal.usecase.CreateGoalUseCase;
import ac.dnd.dodal.application.goal.usecase.AchieveGoalUseCase;
import ac.dnd.dodal.application.goal.usecase.DeleteGoalUseCase;
import ac.dnd.dodal.application.goal.repository.GoalCommandRepository;

@Service
@RequiredArgsConstructor
public class GoalCommandService
        implements CreateGoalUseCase, AchieveGoalUseCase, DeleteGoalUseCase {

    private final GoalCommandRepository goalCommandRepository;

    @Override
    public Long create(CreateGoalCommand command) {
        Goal goal = CreateGoalCommand.toEntity(command);

        return goalCommandRepository.save(goal).getGoalId();
    }

    @Override
    public void achieve(AchieveGoalCommand command) {
        Goal goal = goalCommandRepository.findById(command.goalId())
                .orElseThrow(() -> new NotFoundException(GoalExceptionCode.GOAL_NOT_FOUND));
        goal.achieve(command.userId());

        goalCommandRepository.save(goal);
    }

    @Override
    public void delete(DeleteGoalCommand command) {
        Goal goal = goalCommandRepository.findById(command.goalId())
                .orElseThrow(() -> new NotFoundException(GoalExceptionCode.GOAL_NOT_FOUND));
        goal.delete(command.userId());

        goalCommandRepository.save(goal);
    }
}
