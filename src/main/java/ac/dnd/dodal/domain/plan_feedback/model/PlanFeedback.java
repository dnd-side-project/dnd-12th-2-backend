package ac.dnd.dodal.domain.plan_feedback.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Getter;

import ac.dnd.dodal.common.model.BaseEntity;
import ac.dnd.dodal.domain.plan.model.Plan;

@IdClass(PlanFeedbackId.class)
@Entity(name = "plan_feedbacks")
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PlanFeedback extends BaseEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @Id
    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private String indicator;

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public PlanFeedback(String question, String indicator) {
        this(null, question, indicator);
    }

    public PlanFeedback(Plan plan, String question, String indicator) {
        super();
        this.plan = plan;
        this.question = question;
        this.indicator = indicator;
    }
}
