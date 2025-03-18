package ac.dnd.dodal.application.goal.dto;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Set;

import ac.dnd.dodal.application.plan.dto.command.AddSamePlanCommand;
import ac.dnd.dodal.application.plan.dto.command.AddNewPlanCommand;
import ac.dnd.dodal.application.plan.dto.command.CreatePlanAndHistoryCommand;

public class AddPlanCommandFixture {

    public static AddSamePlanCommand addSamePlanCommand(Long userId, Long goalId, Long planId) {
        return new AddSamePlanCommand(userId, goalId, planId,
                LocalDateTime.now(), LocalDateTime.now(), Set.of(DayOfWeek.MONDAY), 1);
    }

    public static AddNewPlanCommand addNewPlanCommand(Long userId, Long goalId, Long planId) {
        return new AddNewPlanCommand(userId, goalId, planId, "new title",
                LocalDateTime.now(), LocalDateTime.now().plusDays(1));
    }

    public static CreatePlanAndHistoryCommand createPlanAndHistoryCommand(Long userId, Long goalId) {
        return new CreatePlanAndHistoryCommand(userId, goalId, "create title", LocalDateTime.now(),
                LocalDateTime.now().plusDays(1));
    }
}
