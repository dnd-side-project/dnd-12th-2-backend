package ac.dnd.dodal.application.plan_feedback.service;

import ac.dnd.dodal.IntegrationTest;
import ac.dnd.dodal.domain.plan.PlanFixture;
import ac.dnd.dodal.domain.plan.event.DeletedPlanEvent;
import ac.dnd.dodal.domain.plan.model.Plan;
import ac.dnd.dodal.domain.plan_feedback.model.PlanFeedback;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlanFeedbackEventListenerTest extends IntegrationTest {

    private static final Logger log = LoggerFactory.getLogger(PlanFeedbackEventListenerTest.class);
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private PlanFeedbackService planFeedbackService;

    @Test
    @DisplayName("계획이 삭제되면 해당 계획과 관련된 모든 계획 피드백이 삭제된다.")
    void delete_all_plan_feedbacks_when_plan_deleted() {
        // given
        Plan plan = PlanFixture.plan();
        List<PlanFeedback> planFeedbacks = planFeedbackService.findAllByPlanId(plan.getPlanId());
        assertThat(planFeedbacks).hasSize(3);
        assertThat(planFeedbacks.getFirst().getCreatedAt()).isNotNull();
        assertThat(planFeedbacks.getFirst().getDeletedAt()).isNull();
        assertThat(planFeedbacks.getLast().getDeletedAt()).isNull();
        // when
        eventPublisher.publishEvent(new DeletedPlanEvent(
                plan.getPlanId(), plan.getHistory().getHistoryId(), plan.getStatus()
        ));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // then
        assertThat(planFeedbacks.getFirst().getDeletedAt()).isNotNull();
        assertThat(planFeedbacks.getLast().getDeletedAt()).isNotNull();
    }
}
