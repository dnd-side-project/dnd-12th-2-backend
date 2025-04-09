package ac.dnd.dodal.domain.feedback;

import ac.dnd.dodal.domain.plan_feedback.model.PlanFeedback;
import java.time.LocalDateTime;
import ac.dnd.dodal.domain.plan.PlanFixture;
import ac.dnd.dodal.domain.plan.model.Plan;

public class PlanFeedbackFixture {

    private static final Plan PLAN = PlanFixture.plan();

    public static PlanFeedback planFeedback() {
        return new PlanFeedback(PLAN, "question", "indicator");
    }

    public static PlanFeedback deletedPlanFeedback() {
        return new PlanFeedback(PLAN, "question", "indicator");
    }
}
