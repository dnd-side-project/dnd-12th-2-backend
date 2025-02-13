package ac.dnd.dodal.ui.plan.response;

import java.time.LocalDateTime;

import ac.dnd.dodal.domain.plan.enums.PlanStatus;
import ac.dnd.dodal.domain.plan.model.Plan;

public record PlanElement(
    Long planId,
    String title,
    PlanStatus status,
    String guide,
    LocalDateTime completedDate
) {

    public static PlanElement of(Plan plan) {
        return new PlanElement(
            plan.getPlanId(),
            plan.getTitle(),
            plan.getStatus(),
            plan.getGuide(),
            plan.getCompletedDate()
        );
    }
}
