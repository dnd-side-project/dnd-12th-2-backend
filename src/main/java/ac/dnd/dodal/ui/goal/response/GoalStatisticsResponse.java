package ac.dnd.dodal.ui.goal.response;

import ac.dnd.dodal.domain.goal.model.GoalStatistics;

public record GoalStatisticsResponse(
    Long goalId,
    int successCount,
    int failureCount
) {

    public static GoalStatisticsResponse of(GoalStatistics goalStatistics) {
        return new GoalStatisticsResponse(
            goalStatistics.getGoal().getGoalId(),
            goalStatistics.getSuccessCount(),
            goalStatistics.getFailureCount()
        );
    }
}
