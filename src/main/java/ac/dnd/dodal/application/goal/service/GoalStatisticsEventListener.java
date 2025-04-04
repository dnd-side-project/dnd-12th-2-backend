package ac.dnd.dodal.application.goal.service;

import ac.dnd.dodal.domain.goal.event.DeletedGoalEvent;
import ac.dnd.dodal.domain.user.event.UserWithdrawnEvent;
import lombok.RequiredArgsConstructor;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import ac.dnd.dodal.domain.goal.model.GoalStatistics;
import ac.dnd.dodal.domain.goal.event.GoalCreatedEvent;
import ac.dnd.dodal.domain.plan.event.PlanCompletedEvent;

@Component
@RequiredArgsConstructor
public class GoalStatisticsEventListener {

    private final GoalStatisticsService goalStatisticsService;

    @EventListener
    public void handleGoalCreatedEvent(GoalCreatedEvent event) {
        GoalStatistics goalStatistics = new GoalStatistics(event.getGoal());

        goalStatisticsService.save(goalStatistics);
    }

    @EventListener
    public void handlePlanCompletedEvent(PlanCompletedEvent event) {
        GoalStatistics goalStatistics = goalStatisticsService.findByIdOrThrow(event.getGoalId());

        goalStatistics.incrementCount(event.getStatus());
        goalStatisticsService.save(goalStatistics);
    }

    @EventListener
    public void handleDeletedGoalEvent(DeletedGoalEvent event) {
        goalStatisticsService.delete(event.getGoalId());
    }
}