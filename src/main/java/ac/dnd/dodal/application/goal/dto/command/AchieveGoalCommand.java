package ac.dnd.dodal.application.goal.dto.command;

public record AchieveGoalCommand(
    Long userId,
    Long goalId) {
}
