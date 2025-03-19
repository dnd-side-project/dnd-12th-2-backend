package ac.dnd.dodal.application.goal.dto.command;

public record UpdateGoalCommand(
        Long userId,
        Long goalId,
        String title
) {}
