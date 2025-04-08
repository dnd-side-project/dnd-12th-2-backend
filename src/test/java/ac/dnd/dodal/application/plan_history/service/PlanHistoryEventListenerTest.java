package ac.dnd.dodal.application.plan_history.service;

import static org.assertj.core.api.Assertions.assertThat;

import ac.dnd.dodal.IntegrationTest;
import ac.dnd.dodal.application.plan_history.repository.PlanHistoryRepository;
import ac.dnd.dodal.domain.goal.GoalFixture;
import ac.dnd.dodal.domain.goal.event.DeletedGoalEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class PlanHistoryEventListenerTest extends IntegrationTest {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private PlanHistoryCommandService planHistoryCommandService;

    @Autowired
    private PlanHistoryRepository planHistoryRepository;

    @Test
    @DisplayName("목표 삭제 시 목표에 대항하는 계획 이력도 삭제된다.")
    void delete_all_plan_histories_when_goal_deleted() {
        // given
        Long goalId = GoalFixture.goal().getGoalId();
        assertThat(planHistoryRepository.findAllByGoalId(goalId)).isNotEmpty();
        assertThat(planHistoryRepository.findAllByGoalId(goalId).getFirst().getDeletedAt()).isNull();
        assertThat(planHistoryRepository.findAllByGoalId(goalId).getLast().getDeletedAt()).isNull();

        // when
        eventPublisher.publishEvent(new DeletedGoalEvent(goalId));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // then
        assertThat(planHistoryRepository.findAllByGoalId(goalId).getFirst().getDeletedAt()).isNotNull();
        assertThat(planHistoryRepository.findAllByGoalId(goalId).getLast().getDeletedAt()).isNotNull();
    }
}
