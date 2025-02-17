package ac.dnd.dodal.ui.goal;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.PageRequest;

import ac.dnd.dodal.common.annotation.UserId;
import ac.dnd.dodal.application.plan_history.dto.query.GetHistoriesOfGoalQuery;
import ac.dnd.dodal.application.plan_history.usecase.GetHistoriesOfGoalUseCase;
import ac.dnd.dodal.common.response.ApiResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/goals/{goalId}/plan-histories")
public class GoalPlanHistoryController {

    private final GetHistoriesOfGoalUseCase getHistoriesOfGoalUseCase;

    @GetMapping
    public ApiResponse<?> getPlanHistories(
        @UserId Long userId,
        @PathVariable Long goalId,
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "3") Integer size
    ) {
        GetHistoriesOfGoalQuery query = new GetHistoriesOfGoalQuery(
            userId, goalId, PageRequest.of(page, size));

        return ApiResponse.success(getHistoriesOfGoalUseCase.getHistoriesOfGoal(query));
    }
}
