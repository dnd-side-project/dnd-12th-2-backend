package ac.dnd.dodal.application.goal.service;

import ac.dnd.dodal.application.goal.dto.command.DeleteAllGoalCommand;
import ac.dnd.dodal.domain.user.event.UserWithdrawnEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GoalEventListener {

    private final GoalCommandService goalCommandService;

    @Async
    @EventListener
    public void handleUserWithdrawnEvent(UserWithdrawnEvent event) {
        goalCommandService.deleteAll(new DeleteAllGoalCommand(event.getUserId()));
    }
}
