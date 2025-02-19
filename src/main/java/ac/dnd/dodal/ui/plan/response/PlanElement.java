package ac.dnd.dodal.ui.plan.response;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import ac.dnd.dodal.domain.plan.model.Plan;

public record PlanElement(
    Long planId,
    String title,
    String status,
    String guide,
        
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime startDate,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime endDate,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime completedDate
) {

    public static PlanElement of(Plan plan) {
        return new PlanElement(
            plan.getPlanId(),
            plan.getTitle(),
            plan.getStatus().getValue(),
            plan.getGuide(),
            plan.getStartDate(),
            plan.getEndDate(),
            plan.getCompletedDate()
        );
    }
}
