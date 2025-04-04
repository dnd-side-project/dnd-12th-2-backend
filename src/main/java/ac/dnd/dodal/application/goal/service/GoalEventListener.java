package ac.dnd.dodal.application.goal.service;

import ac.dnd.dodal.application.goal.dto.command.DeleteAllGoalCommand;
import ac.dnd.dodal.domain.user.event.UserWithdrawnEvent;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class GoalEventListener {

    private final GoalCommandService goalCommandService;

    @EventListener
    public void handleUserWithdrawnEvent(UserWithdrawnEvent event) {
        goalCommandService.deleteAll(new DeleteAllGoalCommand(event.getUserId()));
    }
}
