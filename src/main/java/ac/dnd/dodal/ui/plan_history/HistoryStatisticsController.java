package ac.dnd.dodal.ui.plan_history;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ac.dnd.dodal.common.annotation.UserId;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.application.plan_history.usecase.GetHistoryStatisticsUseCase;
import ac.dnd.dodal.application.plan_history.dto.query.GetHistoryStatisticsQuery;
import ac.dnd.dodal.ui.plan_history.response.HistoryResponse;

@RestController
@RequestMapping("/api/goals/{goalId}/plan-histories/{historyId}/statistics")
@RequiredArgsConstructor
public class HistoryStatisticsController {

    private final GetHistoryStatisticsUseCase getHistoryStatisticsUseCase;

    @GetMapping
    public ApiResponse<HistoryResponse> getHistoryStatistics(
        @UserId Long userId,
        @PathVariable Long goalId,
        @PathVariable Long historyId) {
        GetHistoryStatisticsQuery query = new GetHistoryStatisticsQuery(userId, goalId, historyId);

        return ApiResponse.success(getHistoryStatisticsUseCase.getHistoryStatistics(query));
    }

}
