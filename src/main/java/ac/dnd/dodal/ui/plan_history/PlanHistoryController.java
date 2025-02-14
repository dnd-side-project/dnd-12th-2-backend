package ac.dnd.dodal.ui.plan_history;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.PageRequest;

import ac.dnd.dodal.common.annotation.UserId;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.common.response.ApiResponse.PageResponse;
import ac.dnd.dodal.application.plan.usecase.GetPlansOfHistoryUseCase;
import ac.dnd.dodal.application.plan.dto.query.GetPlansOfHistoryQuery;
import ac.dnd.dodal.ui.plan.response.PlanElement;

@RestController
@RequestMapping("/api/goals/{goalId}/plan-histories")
@RequiredArgsConstructor
public class PlanHistoryController {

    private final GetPlansOfHistoryUseCase getPlansOfHistoryUseCase;

    @GetMapping("/{historyId}")
    public ApiResponse<PageResponse<PlanElement>> getPlansOfHistory(
        @UserId Long userId,
        @PathVariable Long goalId,
        @PathVariable Long historyId,
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "3") Integer size) {
        GetPlansOfHistoryQuery query = new GetPlansOfHistoryQuery(
            userId, goalId, historyId, PageRequest.of(page, size));

        return ApiResponse.success(getPlansOfHistoryUseCase.getPlansOfHistory(query));
    }
}
