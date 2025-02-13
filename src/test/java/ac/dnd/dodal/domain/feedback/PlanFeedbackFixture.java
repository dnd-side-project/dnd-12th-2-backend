package ac.dnd.dodal.domain.feedback;

import ac.dnd.dodal.domain.plan_feedback.model.PlanFeedback;
import java.time.LocalDateTime;
import ac.dnd.dodal.domain.plan.PlanFixture;
import ac.dnd.dodal.domain.plan.model.Plan;

public class PlanFeedbackFixture {

    private static final Long PLAN_FEEDBACK_ID = 1L;
    private static final Plan PLAN = PlanFixture.plan();

    public static PlanFeedback planFeedback() {
        return new PlanFeedback(PLAN_FEEDBACK_ID, PLAN, "question", "indicator",
                LocalDateTime.now(), LocalDateTime.now(), null);
    }

    public static PlanFeedback deletedPlanFeedback() {
        return new PlanFeedback(PLAN_FEEDBACK_ID, PLAN, "question", "indicator",
                LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
    }
}
