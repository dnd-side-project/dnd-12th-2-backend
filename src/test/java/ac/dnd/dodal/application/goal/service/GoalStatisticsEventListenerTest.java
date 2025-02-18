package ac.dnd.dodal.application.goal.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import ac.dnd.dodal.IntegrationTest;
import ac.dnd.dodal.domain.goal.GoalFixture;
import ac.dnd.dodal.domain.goal.event.GoalCreatedEvent;
import ac.dnd.dodal.domain.goal.model.Goal;
import ac.dnd.dodal.domain.goal.model.GoalStatistics;
import ac.dnd.dodal.domain.plan.PlanFixture;
import ac.dnd.dodal.domain.plan.event.PlanCompletedEvent;
import ac.dnd.dodal.domain.plan.model.Plan;
import ac.dnd.dodal.domain.plan_feedback.model.PlanFeedback;

public class GoalStatisticsEventListenerTest extends IntegrationTest {

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    GoalStatisticsService goalStatisticsService;

    @Autowired
    GoalStatisticsEventListener goalStatisticsEventListener;

    @Test
    @DisplayName("Goal Created Event Listener Test")
    void goal_created_event_listener_test() {
        // given
        Goal goal = goalService.saveAndFlush(GoalFixture.goal());
        Long goalId = goal.getGoalId();

        // when
        eventPublisher.publishEvent(new GoalCreatedEvent(goal));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        GoalStatistics goalStatistics = goalStatisticsService.findByIdOrThrow(goalId);
        assertThat(goalStatistics.getGoal().getGoalId()).isEqualTo(goalId);
    }

    @Test
    @DisplayName("Goal Statistics Plan Completed Event Listener Test")
    void goal_statistics_plan_completed_event_listener_test() {
        // given
        Plan plan = PlanFixture.successPlan();
        Long goalId = plan.getGoal().getGoalId();
        PlanFeedback feedback = new PlanFeedback(
                plan, "question", "indicator", null, null, null);
        GoalStatistics previousGoalStatistics = goalStatisticsService.findByIdOrThrow(goalId);
        int previousSuccessCount = previousGoalStatistics.getSuccessCount();
        int previousFailureCount = previousGoalStatistics.getFailureCount();

        // when
        eventPublisher.publishEvent(PlanCompletedEvent.of(plan, feedback));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // then
        GoalStatistics goalStatistics = goalStatisticsService.findByIdOrThrow(goalId);
        assertThat(goalStatistics.getSuccessCount())
                .isEqualTo(previousSuccessCount + 1);
        assertThat(goalStatistics.getFailureCount())
                .isEqualTo(previousFailureCount);
    }
}
