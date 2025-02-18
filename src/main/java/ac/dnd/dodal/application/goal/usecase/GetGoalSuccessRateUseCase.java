package ac.dnd.dodal.application.goal.usecase;

import ac.dnd.dodal.ui.goal.response.GoalStatisticsResponse;
import ac.dnd.dodal.application.goal.dto.query.GetGoalSuccessRateQuery;

public interface GetGoalSuccessRateUseCase {

    GoalStatisticsResponse getGoalSuccessRate(GetGoalSuccessRateQuery query);
}
