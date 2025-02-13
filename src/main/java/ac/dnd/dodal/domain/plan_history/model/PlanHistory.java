package ac.dnd.dodal.domain.plan_history.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_id", nullable = false)
    private Goal goal;

    public void addPlan(Plan plan, Plan previousPlan, Plan latestPlan) {
        validateDeleted();
        if (latestPlan != null && previousPlan != null && (
            !previousPlan.isCompleted() || !previousPlan.equals(latestPlan)
        )) {
            throw new BadRequestException(PlanHistoryExceptionCode.PLAN_CAN_BE_ADDED_ONLY_TO_LAST);
        }

        plan.setHistory(this);
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    // Todo: 추후 delete 비동기 처리
    public void delete() {
        validateDeleted();

        super.delete();
    }

    public PlanHistory(Long historyId, Goal goal,
            LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this.historyId = historyId;
        this.goal = goal;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    private void validateDeleted() {
        if (this.deletedAt != null) {
            throw new BadRequestException(PlanHistoryExceptionCode.PLAN_HISTORY_ALREADY_DELETED);
        }
    }
}
