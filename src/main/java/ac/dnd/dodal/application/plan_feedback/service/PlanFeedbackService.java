package ac.dnd.dodal.application.plan_feedback.service;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import ac.dnd.dodal.application.plan_feedback.repository.PlanFeedbackRepository;
import ac.dnd.dodal.domain.plan_feedback.model.PlanFeedback;

@Service
@RequiredArgsConstructor
public class PlanFeedbackService {

    private final PlanFeedbackRepository planFeedbackRepository;

    public PlanFeedback save(PlanFeedback planFeedback) {
        return planFeedbackRepository.save(planFeedback);
    }

    public List<PlanFeedback> saveAll(List<PlanFeedback> planFeedbacks) {
        return planFeedbackRepository.saveAll(planFeedbacks);
    }
}
