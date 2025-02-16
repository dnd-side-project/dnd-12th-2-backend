package ac.dnd.dodal.application.plan.usecase;

import java.util.List;

import ac.dnd.dodal.application.plan.dto.query.GetWeeklyAchievementRateOfGoalQuery;
import ac.dnd.dodal.ui.goal.response.DailyAchievementRateElement;

public interface GetWeeklyAchievementRateOfGoalUseCase {

    List<DailyAchievementRateElement> getWeeklyAchievementRateOfGoal(
        GetWeeklyAchievementRateOfGoalQuery query);
}
