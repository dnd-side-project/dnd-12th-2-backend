package ac.dnd.dodal.application.plan.service;

import ac.dnd.dodal.application.plan.dto.command.DeleteAllPlanCommand;
import ac.dnd.dodal.domain.goal.event.DeletedGoalEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlanEventListener {

    private final PlanCommandService planCommandService;

    @Async
    @EventListener
    public void handleDeletedGoalEvent(DeletedGoalEvent event) {
    planCommandService.deleteAllByGoalId(new DeleteAllPlanCommand(event.getGoalId()));
    }
}
