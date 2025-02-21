package ac.dnd.dodal.application.plan.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import ac.dnd.dodal.domain.plan.enums.PlanStatus;
import ac.dnd.dodal.ui.plan.response.PlanElement;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PlanModel {

    private Long planId;
    private Long historyId;
    private Long goalId;
    private String title;
    private PlanStatus status;
    private String guide;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime completedDate;

    public PlanElement toPlanElement() {
        return new PlanElement(
            planId, title, status.getValue(), guide, startDate, endDate, completedDate);
    }
}
