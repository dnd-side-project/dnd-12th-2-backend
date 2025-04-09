package ac.dnd.dodal.application.plan_feedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ac.dnd.dodal.domain.plan_feedback.model.PlanFeedback;
import ac.dnd.dodal.domain.plan_feedback.model.PlanFeedbackId;

import java.util.List;

@Repository
public interface PlanFeedbackRepository extends JpaRepository<PlanFeedback, PlanFeedbackId> {

    @Query("SELECT pf FROM plan_feedbacks pf WHERE pf.plan.planId = :planId AND pf.deletedAt IS NULL")
    List<PlanFeedback> findAllByPlanId(Long planId);
}
