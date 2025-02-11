package ac.dnd.dodal.domain.guide.service;

import java.util.List;
import ac.dnd.dodal.domain.plan_feedback.model.PlanFeedback;

public class GuidianceService {

    // Todo: 추후 새 기록 생성 (New history), 계획 재설정하기에 따른 가이드 분리
    public static String generateGuide(List<PlanFeedback> feedbacks) {
        return feedbacks.get(0).getIndicator();
    }
}
