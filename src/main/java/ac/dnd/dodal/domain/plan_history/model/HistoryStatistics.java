package ac.dnd.dodal.domain.plan_history.model;

import lombok.Getter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import ac.dnd.dodal.domain.plan.enums.PlanStatus;

@Entity(name = "history_statistics")
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class HistoryStatistics {

    @Id
    private Long historyId;

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
        if (successCount < 0 || failureCount < 0) {
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
}


