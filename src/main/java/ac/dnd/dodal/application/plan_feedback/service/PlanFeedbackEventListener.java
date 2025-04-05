package ac.dnd.dodal.application.plan_feedback.service;

import ac.dnd.dodal.application.plan_feedback.dto.command.DeleteAllPlanFeedbackCommand;
import ac.dnd.dodal.domain.plan.event.DeletedPlanEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlanFeedbackEventListener {

    private final PlanFeedbackCommandService planFeedbackCommandService;

    @EventListener
    public void handleDeletedPlanEvent(DeletedPlanEvent event) {
        planFeedbackCommandService.deleteAll(
                new DeleteAllPlanFeedbackCommand(
                        event.getPlanId(),
                        event.getHistoryId(),
                        event.getStatus()
                )
        );
    }
}
