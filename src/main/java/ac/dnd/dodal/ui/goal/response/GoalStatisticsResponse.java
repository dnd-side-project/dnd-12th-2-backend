package ac.dnd.dodal.ui.goal.response;

import ac.dnd.dodal.domain.goal.model.GoalStatistics;

public record GoalStatisticsResponse(
    Long goalId,
    String title,
    int successCount,
    int failureCount,
    int totalCount
) {

    public static GoalStatisticsResponse of(GoalStatistics goalStatistics) {
        return new GoalStatisticsResponse(
            goalStatistics.getGoal().getGoalId(),
            goalStatistics.getGoal().getTitle(),
            goalStatistics.getSuccessCount(),
            goalStatistics.getFailureCount(),
            goalStatistics.getTotalCount()
        );
    }
}
