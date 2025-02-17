package ac.dnd.dodal.application.plan.service;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import ac.dnd.dodal.application.plan.repository.PlanRepository;
import ac.dnd.dodal.application.plan.usecase.GetPlansOfHistoryUseCase;
import ac.dnd.dodal.common.exception.ForbiddenException;
import ac.dnd.dodal.application.plan.dto.query.GetPlansOfHistoryQuery;
import ac.dnd.dodal.ui.plan.response.PlanElement;

@Service
@RequiredArgsConstructor
public class PlanQueryService implements GetPlansOfHistoryUseCase {

    private final PlanRepository planRepository;

    @Override
    public Page<PlanElement> getPlansOfHistory(GetPlansOfHistoryQuery query) {
        if (!isExistByUserIdAndGoalIdAndHistoryId(
            query.userId(), query.goalId(), query.historyId())) {
            throw new ForbiddenException();
        }
        return planRepository.findAllByHistoryId(query.historyId(), query.pageable());
    }

    public boolean isExistByUserIdAndGoalIdAndHistoryId(
        Long userId, Long goalId, Long historyId) {
        return planRepository.isExistByUserIdAndGoalIdAndHistoryId(userId, goalId, historyId);
    }
}
