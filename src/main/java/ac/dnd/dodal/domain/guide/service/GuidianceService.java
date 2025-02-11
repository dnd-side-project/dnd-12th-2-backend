package ac.dnd.dodal.domain.guide.service;

import ac.dnd.dodal.domain.plan_feedback.model.PlanFeedback;

public class GuidianceService {

    public static String generateGuide(PlanFeedback feedback) {
        return feedback.getIndicator();
    }
}
