package ac.dnd.dodal.ui.plan.response;

import java.time.LocalDateTime;

import ac.dnd.dodal.domain.plan.model.Plan;

public record PlanElement(
    Long planId,
    String title,
    String status,
    String guide,
    LocalDateTime completedDate
) {

    public static PlanElement of(Plan plan) {
        return new PlanElement(
            plan.getPlanId(),
            plan.getTitle(),
            plan.getStatus().getValue(),
            plan.getGuide(),
            plan.getCompletedDate()
        );
    }
}
