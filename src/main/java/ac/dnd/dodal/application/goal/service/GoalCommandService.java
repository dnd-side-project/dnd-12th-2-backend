package ac.dnd.dodal.application.goal.service;

import ac.dnd.dodal.application.goal.usecase.UpdateGoalUseCase;
import ac.dnd.dodal.domain.goal.event.DeletedGoalEvent;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.ApplicationEventPublisher;

import ac.dnd.dodal.domain.goal.model.Goal;
import ac.dnd.dodal.domain.goal.event.GoalCreatedEvent;
import ac.dnd.dodal.application.goal.dto.command.*;
import ac.dnd.dodal.application.goal.usecase.CreateGoalUseCase;
import ac.dnd.dodal.application.goal.usecase.AchieveGoalUseCase;
import ac.dnd.dodal.application.goal.usecase.DeleteGoalUseCase;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GoalCommandService
        implements CreateGoalUseCase, UpdateGoalUseCase, AchieveGoalUseCase, DeleteGoalUseCase {

    private final GoalService goalService;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Long create(CreateGoalCommand command) {
        Goal goal = CreateGoalCommand.toEntity(command);

        goalService.saveAndFlush(goal);
        eventPublisher.publishEvent(new GoalCreatedEvent(goal));
        return goal.getGoalId();
    }

    @Override
    public void updateTitle(UpdateGoalCommand command){
        Goal goal = goalService.findByIdOrThrow(command.goalId());
        goal.updateTitle(command.title());

        goalService.save(goal);
    }

    @Override
    public void achieve(AchieveGoalCommand command) {
        Goal goal = goalService.findByIdOrThrow(command.goalId());
        goal.achieve(command.userId());

        goalService.save(goal);
    }

    // Todo: Goal delete event 발행
    @Override
    public void delete(DeleteGoalCommand command) {
        Goal goal = goalService.findByIdOrThrow(command.goalId());
        goal.delete(command.userId());

        goalService.save(goal);
    }

    public void deleteAll(DeleteAllGoalCommand command) {
        List<Goal> goals = goalService.findAllByUserId(command.userId());
        goals
                .forEach(goal -> {
                    goal.delete(command.userId());
                    eventPublisher.publishEvent(new DeletedGoalEvent(goal.getGoalId()));
                });

        goalService.saveAll(goals);
    }
}
