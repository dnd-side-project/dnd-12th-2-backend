package ac.dnd.dodal.application.goal.dto.command;

public record DeleteGoalCommand(
    Long userId,
    Long goalId) {
}
