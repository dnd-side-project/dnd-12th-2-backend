package ac.dnd.dodal.application.goal.service;

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
    public void handlePlanCompletedEvent(PlanCompletedEvent event) {
        GoalStatistics goalStatistics = goalStatisticsService.findByIdOrThrow(event.getGoalId());

        goalStatistics.incrementCount(event.getStatus());
        goalStatisticsService.save(goalStatistics);
    }
}