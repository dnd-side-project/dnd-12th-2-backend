package ac.dnd.dodal.domain.goal.model;

import lombok.Getter;
import lombok.EqualsAndHashCode;
import ac.dnd.dodal.domain.plan.enums.PlanStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "goal_statistics")
@Getter
@EqualsAndHashCode
public class GoalStatistics {

    @Id
    private Long goalId;

    private int successCount;
    private int failureCount;

    public void incrementCount(PlanStatus status) {
        if (status == PlanStatus.SUCCESS) {
            this.successCount++;
        } else {
            this.failureCount++;
        }
    }

    public void decrementCount(PlanStatus status) {
        if (status == PlanStatus.SUCCESS) {
            this.successCount--;
        } else {
            this.failureCount--;
        }
        if (this.successCount < 0 || this.failureCount < 0) {
            throw new IllegalArgumentException("Count cannot be negative");
        }
    }

    public void reset() {
        this.successCount = 0;
        this.failureCount = 0;
    }

    public void update(int successCount, int failureCount) {
        if (successCount < 0 || failureCount < 0) {
            throw new IllegalArgumentException("Count cannot be negative");
        }
        this.successCount = successCount;
        this.failureCount = failureCount;
    }

    public GoalStatistics(Long goalId) {
        this(goalId, 0, 0);
    }

    public GoalStatistics(Long goalId, int successCount, int failureCount) {
        if (goalId == null) {
            throw new IllegalArgumentException("Goal ID is null");
        }
        if (successCount < 0 || failureCount < 0) {
            throw new IllegalArgumentException("Count cannot be negative");
        }
        this.goalId = goalId;
        this.successCount = successCount;
        this.failureCount = failureCount;
    }
}