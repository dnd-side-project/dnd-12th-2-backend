package ac.dnd.dodal.ui.goal;

import java.time.LocalDate;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.common.annotation.UserId;
import ac.dnd.dodal.application.goal.dto.query.GetGoalSuccessRateQuery;
import ac.dnd.dodal.application.plan.usecase.GetWeeklyAchievementRateOfGoalUseCase;
import ac.dnd.dodal.application.goal.usecase.GetGoalSuccessRateUseCase;
import ac.dnd.dodal.application.plan.dto.query.GetWeeklyAchievementRateOfGoalQuery;

@RestController
@RequestMapping("/api/goals/{goalId}/statistics")
@RequiredArgsConstructor
public class GoalStatisticsController {

    private final GetWeeklyAchievementRateOfGoalUseCase getWeeklyAchievementRateOfGoalUseCase;
    private final GetGoalSuccessRateUseCase getGoalSuccessRateUseCase;

    @GetMapping()
    public ApiResponse<?> getGoalSuccessRate(
        @UserId Long userId,
        @PathVariable Long goalId
    ) {
        GetGoalSuccessRateQuery query = new GetGoalSuccessRateQuery(userId, goalId);

        return ApiResponse.success(
            getGoalSuccessRateUseCase.getGoalSuccessRate(query));
    }

    @GetMapping("/weekly")
    public ApiResponse<?> getWeeklyAchievementRateOfGoal(
        @UserId Long userId,
        @PathVariable Long goalId,
        @RequestParam LocalDate date
    ) {
        GetWeeklyAchievementRateOfGoalQuery query 
            = new GetWeeklyAchievementRateOfGoalQuery(userId, goalId, date);

        return ApiResponse.success(
            getWeeklyAchievementRateOfGoalUseCase.getWeeklyAchievementRateOfGoal(query));
    }
}
