package ac.dnd.dodal.application.plan_feedback.service;

import ac.dnd.dodal.application.plan_feedback.dto.command.DeleteAllPlanFeedbackCommand;
import ac.dnd.dodal.domain.plan_feedback.model.PlanFeedback;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PlanFeedbackCommandService {

    private final PlanFeedbackService planFeedbackService;

    public void deleteAll(DeleteAllPlanFeedbackCommand command) {
        List<PlanFeedback> planFeedbacks = planFeedbackService.findAllByPlanId(command.getPlanId());
        for (PlanFeedback feedback : planFeedbacks) {
            feedback.delete();
        }
        planFeedbackService.saveAll(planFeedbacks);
    }
}
