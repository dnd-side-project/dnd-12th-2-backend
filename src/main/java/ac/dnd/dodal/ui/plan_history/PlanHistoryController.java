package ac.dnd.dodal.ui.plan_history;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.dnd.dodal.common.annotation.UserId;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.common.response.ApiResponse.PageResponse;
import ac.dnd.dodal.ui.plan.response.PlanElement;
import ac.dnd.dodal.application.plan.usecase.GetPlansOfHistoryUseCase;

@RestController
@RequestMapping("/api/goals/{goalId}/plan-histories")
@RequiredArgsConstructor
public class PlanHistoryController {

    private final GetPlansOfHistoryUseCase getPlansOfHistoryUseCase;

    @GetMapping("/{historyId}")
    public ApiResponse<PageResponse<PlanElement>> getPlansOfHistory(
        @UserId Long userId,
        @PathVariable Long goalId,
        @PathVariable Long historyId) {
        return ApiResponse.success(getPlansOfHistoryUseCase.getPlansOfHistory(userId, goalId, historyId));
    }
}
