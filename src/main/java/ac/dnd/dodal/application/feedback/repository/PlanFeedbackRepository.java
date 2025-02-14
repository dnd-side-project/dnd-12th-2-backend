package ac.dnd.dodal.application.feedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ac.dnd.dodal.domain.plan_feedback.model.PlanFeedback;
import ac.dnd.dodal.domain.plan_feedback.model.PlanFeedbackId;

@Repository
public interface PlanFeedbackRepository extends JpaRepository<PlanFeedback, PlanFeedbackId> {
}
