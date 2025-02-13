package ac.dnd.dodal.application.feedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ac.dnd.dodal.domain.plan_feedback.model.PlanFeedback;
import ac.dnd.dodal.domain.plan_feedback.model.PlanFeedbackId;

public interface PlanFeedbackRepository extends JpaRepository<PlanFeedback, PlanFeedbackId> {
}
