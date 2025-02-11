package ac.dnd.dodal.domain.plan_feedback.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;

import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Getter;

import ac.dnd.dodal.common.model.BaseEntity;
import ac.dnd.dodal.domain.plan.model.Plan;

@Entity(name = "plan_feedbacks")
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PlanFeedback extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planFeedbackId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private String indicator;

    public PlanFeedback(String question, String indicator) {
        this.question = question;
        this.indicator = indicator;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
}
