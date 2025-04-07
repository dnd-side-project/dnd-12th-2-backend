package ac.dnd.dodal.application.plan.service;

import static org.assertj.core.api.Assertions.assertThat;

import ac.dnd.dodal.IntegrationTest;
import ac.dnd.dodal.application.plan.repository.PlanRepository;
import ac.dnd.dodal.domain.goal.GoalFixture;
import ac.dnd.dodal.domain.goal.event.DeletedGoalEvent;
import ac.dnd.dodal.domain.plan.model.Plan;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class PlanEventListenerTest extends IntegrationTest {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private PlanService planService;

    @Test
    @DisplayName("목표가 삭제되면 해당 목표에 속한 모든 계획이 삭제된다.")
    void delete_all_plans_when_goal_deleted() {
        // given
        Long goalId = GoalFixture.goal().getGoalId();
        List<Plan> plans = planService.findAllByGoalId(goalId);
        assertThat(plans.getLast().getDeletedAt()).isNull();

        // when
        eventPublisher.publishEvent(new DeletedGoalEvent(goalId));
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // then
        assertThat(plans.getLast().getDeletedAt()).isNotNull();
    }
}
