package ac.dnd.dodal.application.goal.usecase;

import java.util.List;

import ac.dnd.dodal.ui.goal.response.GoalStatisticsResponse;

public interface GetGoalUseCase {

    List<GoalStatisticsResponse> getUnAchievedGoals(Long userId);

    List<GoalStatisticsResponse> getAchievedGoals(Long userId);
}
