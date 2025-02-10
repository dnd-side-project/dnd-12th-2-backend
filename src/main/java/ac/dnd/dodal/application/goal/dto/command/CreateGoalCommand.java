package ac.dnd.dodal.application.goal.dto.command;

import ac.dnd.dodal.domain.goal.model.Goal;

public record CreateGoalCommand(
    Long userId,
    String title) {

    public static Goal toEntity(CreateGoalCommand command) {
        return new Goal(command.userId(), command.title());
    }
}
