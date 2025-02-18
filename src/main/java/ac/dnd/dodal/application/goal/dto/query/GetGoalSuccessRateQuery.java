package ac.dnd.dodal.application.goal.dto.query;

public record GetGoalSuccessRateQuery(
    Long userId,
    Long goalId 
) {
}
