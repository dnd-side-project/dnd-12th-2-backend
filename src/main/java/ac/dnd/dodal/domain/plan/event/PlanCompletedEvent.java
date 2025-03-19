package ac.dnd.dodal.domain.plan.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import ac.dnd.dodal.domain.plan.model.Plan;
import ac.dnd.dodal.domain.plan.enums.PlanStatus;
import ac.dnd.dodal.domain.plan_feedback.model.PlanFeedback;

@Getter
@EqualsAndHashCode
public class PlanCompletedEvent {

    private Long userId;
    private Long goalId;
    private Long historyId;
    private Long planId;
    private PlanStatus status;
    private String question;
    private String indicator;
    private String guide;
    private String title;

    // TODO: 이렇게 가져오면 안됨!!!!!!!! 로직 개선 필수
    public static PlanCompletedEvent of(Plan plan, PlanFeedback feedback) {
        return new PlanCompletedEvent(
            plan.getGoal().getUserId(),
            plan.getGoal().getGoalId(),
            plan.getHistory().getHistoryId(),
            plan.getPlanId(),
            plan.getStatus(),
            feedback.getQuestion(),
            feedback.getIndicator(),
            plan.getGuide(),
            plan.getTitle()
        );
    }

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
        String guide,
        String title) {
        if (userId == null || goalId == null || historyId == null || planId == null
                || status == null || question == null || indicator == null || guide == null || title == null) {
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
        this.title = title;
    }
}