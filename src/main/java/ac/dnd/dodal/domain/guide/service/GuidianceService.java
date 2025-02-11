package ac.dnd.dodal.domain.guide.service;

import java.util.List;
import ac.dnd.dodal.domain.plan_feedback.model.PlanFeedback;

public class GuidianceService {

    public static String generateGuide(List<PlanFeedback> feedbacks) {
        return feedbacks.get(0).getIndicator();
    }
}
