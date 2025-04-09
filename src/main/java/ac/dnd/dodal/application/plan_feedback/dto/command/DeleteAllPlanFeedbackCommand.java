package ac.dnd.dodal.application.plan_feedback.dto.command;

import ac.dnd.dodal.domain.plan.enums.PlanStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteAllPlanFeedbackCommand {
  private Long planId;
  private Long historyId;
  private PlanStatus status;
}
