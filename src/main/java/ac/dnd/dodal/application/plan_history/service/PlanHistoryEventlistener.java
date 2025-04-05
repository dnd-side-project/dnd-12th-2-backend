package ac.dnd.dodal.application.plan_history.service;

import ac.dnd.dodal.domain.goal.event.DeletedGoalEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlanHistoryEventlistener {

    private final PlanHistoryCommandService planHistoryCommandService;

    @EventListener
    public void handleDeletedGoalEvent(DeletedGoalEvent event) {
        planHistoryCommandService.deleteAllByGoalId(event.getGoalId());
    }
}
