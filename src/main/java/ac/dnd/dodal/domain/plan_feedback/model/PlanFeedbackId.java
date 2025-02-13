package ac.dnd.dodal.domain.plan_feedback.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import ac.dnd.dodal.domain.plan.model.Plan;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PlanFeedbackId implements Serializable {
    private Plan plan;
    private String question;
}
