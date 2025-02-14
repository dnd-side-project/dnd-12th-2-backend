package ac.dnd.dodal.application.plan.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import ac.dnd.dodal.application.plan.repository.PlanRepository;
import ac.dnd.dodal.application.plan.usecase.GetPlansOfHistoryUseCase;
import ac.dnd.dodal.ui.plan.response.PlanElement;

@Service
@RequiredArgsConstructor
public class PlanQueryService implements GetPlansOfHistoryUseCase {

    private final PlanRepository planRepository;

    @Override
    public Page<PlanElement> getPlansOfHistory(Long userId, Long goalId, Long historyId) {
        return null;
    }
}
