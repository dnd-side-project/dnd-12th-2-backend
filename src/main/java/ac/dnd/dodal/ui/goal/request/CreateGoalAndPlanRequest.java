package ac.dnd.dodal.ui.goal.request;

import java.time.LocalDateTime;

import ac.dnd.dodal.application.goal.dto.command.CreateGoalAndPlanCommand;

public record CreateGoalAndPlanRequest(
    String goalTitle,
    String planTitle,
    LocalDateTime startDate,
    LocalDateTime endDate
) {

    public CreateGoalAndPlanCommand toCreateGoalAndPlanCommand(Long userId) {
        return new CreateGoalAndPlanCommand(userId, goalTitle, planTitle, startDate, endDate);
    }
}
