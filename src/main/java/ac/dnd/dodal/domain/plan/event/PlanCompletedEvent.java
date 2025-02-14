package ac.dnd.dodal.domain.plan.event;

import lombok.Getter;

import ac.dnd.dodal.domain.plan.enums.PlanStatus;

@Getter
public class PlanCompletedEvent {

    private Long userId;
    private Long goalId;
    private Long historyId;
    private Long planId;
    private PlanStatus status;
    private String question;
    private String indicator;
    private String guide;

    public boolean isSuccess() {
        return status == PlanStatus.SUCCESS;
    }

    public PlanCompletedEvent(
        Long userId,
        Long goalId,
        Long historyId,
        Long planId,
        PlanStatus status,
        String question,
        String indicator,
        String guide) {
        if (userId == null || goalId == null || historyId == null || planId == null
                || status == null || question == null || indicator == null || guide == null) {
            throw new IllegalArgumentException("PlanCompletedEvent: Invalid arguments");
        }
        if (status != PlanStatus.SUCCESS && status != PlanStatus.FAILURE) {
            throw new IllegalArgumentException("PlanCompletedEvent: Invalid status");
        }
        this.userId = userId;
        this.goalId = goalId;
        this.historyId = historyId;
        this.planId = planId;
        this.status = status;
        this.question = question;
        this.indicator = indicator;
        this.guide = guide;
    }
}