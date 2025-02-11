package ac.dnd.dodal.domain.plan_history.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import ac.dnd.dodal.common.model.BaseEntity;
import ac.dnd.dodal.common.exception.BadRequestException;
import ac.dnd.dodal.domain.plan.model.Plan;
import ac.dnd.dodal.domain.goal.model.Goal;
import ac.dnd.dodal.domain.plan_history.exception.PlanHistoryExceptionCode;

@Entity(name = "plan_histories")
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PlanHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    @Column(nullable = false)
    private Long goalId;

    @OneToMany(mappedBy = "history", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Plan> plans;

    public void addPlan(Plan plan) {
        validateDeleted();

        plan.setHistory(this);
    }

    public void setGoal(Goal goal) {
        this.goalId = goal.getGoalId();
    }

    public void delete() {
        validateDeleted();

        if (this.plans != null) {
            this.plans.forEach(Plan::delete);
        }
        super.delete();
    }

    private void validateDeleted() {
        if (this.deletedAt != null) {
            throw new BadRequestException(PlanHistoryExceptionCode.PLAN_HISTORY_ALREADY_DELETED);
        }
    }
}
