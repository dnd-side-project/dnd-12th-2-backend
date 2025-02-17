package ac.dnd.dodal.domain.goal.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import ac.dnd.dodal.domain.plan.enums.PlanStatus;

@Entity(name = "goal_statistics")
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class GoalStatistics {

    @Id
    private Long goalId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "goal_id", referencedColumnName = "goal_id")
    private Goal goal;

    private int successCount;
    private int failureCount;
    private int totalCount;

    public void incrementCount(PlanStatus status) {
        if (status == PlanStatus.SUCCESS) {
            this.successCount++;
        } else {
            this.failureCount++;
        }
        this.totalCount++;
    }

    public void decrementCount(PlanStatus status) {
        if (status == PlanStatus.SUCCESS) {
            this.successCount--;
        } else {
            this.failureCount--;
        }
        this.totalCount--;
        if (this.successCount < 0 || this.failureCount < 0 || this.totalCount < 0) {
            throw new IllegalArgumentException("Count cannot be negative");
        }
    }

    public void reset() {
        this.successCount = 0;
        this.failureCount = 0;
        this.totalCount = 0;
    }

    public void update(int successCount, int failureCount) {
        if (successCount < 0 || failureCount < 0) {
            throw new IllegalArgumentException("Count cannot be negative");
        }
        this.successCount = successCount;
        this.failureCount = failureCount;
        this.totalCount = successCount + failureCount;
    }

    public GoalStatistics(Goal goal) {
        this(goal, 0, 0);
    }

    public GoalStatistics(Goal goal, int successCount, int failureCount) {
        if (goal == null) {
            throw new IllegalArgumentException("Goal is null");
        }
        if (successCount < 0 || failureCount < 0) {
            throw new IllegalArgumentException("Count cannot be negative");
        }
        this.goal = goal;
        this.successCount = successCount;
        this.failureCount = failureCount;
        this.totalCount = successCount + failureCount;
    }
}