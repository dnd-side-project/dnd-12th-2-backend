package ac.dnd.dodal.application.plan_history.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import ac.dnd.dodal.common.exception.ForbiddenException;
import ac.dnd.dodal.application.plan.service.PlanService;
import ac.dnd.dodal.application.plan_history.dto.query.GetHistoriesOfGoalQuery;
import ac.dnd.dodal.application.plan_history.dto.query.GetHistoryStatisticsQuery;
import ac.dnd.dodal.application.plan_history.repository.PlanHistoryRepository;
import ac.dnd.dodal.application.plan_history.usecase.GetHistoriesOfGoalUseCase;
import ac.dnd.dodal.application.plan_history.usecase.GetHistoryStatisticsUseCase;
import ac.dnd.dodal.ui.plan_history.response.HistoryResponse;

@Service
@RequiredArgsConstructor
public class PlanHistoryQueryService implements 
    GetHistoryStatisticsUseCase, 
    GetHistoriesOfGoalUseCase {

    private final PlanHistoryRepository planHistoryRepository;
    private final PlanService planService;

    @Override
    public Page<HistoryResponse> getHistoriesOfGoal(GetHistoriesOfGoalQuery query) {
        if (!planService.isExistByUserIdAndGoalId(query.userId(), query.goalId())) {
            throw new ForbiddenException();
        }
        return planHistoryRepository.getHistoryResponsesByGoalId(query.goalId(), query.pageable());
    }

    @Override
    public HistoryResponse getHistoryStatistics(GetHistoryStatisticsQuery query) {
        if (!planService.isExistByUserIdAndGoalId(query.userId(), query.goalId())) {
            throw new ForbiddenException();
        }
        return planHistoryRepository.getHistoryResponseByGoalId(query.goalId(), query.historyId());
    }
}
