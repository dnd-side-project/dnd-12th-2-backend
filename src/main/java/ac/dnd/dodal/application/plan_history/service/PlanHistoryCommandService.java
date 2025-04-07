package ac.dnd.dodal.application.plan_history.service;

import ac.dnd.dodal.domain.plan_history.model.PlanHistory;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class PlanHistoryCommandService {

    private final PlanHistoryService planHistoryService;

    public void deleteAllByGoalId(Long goalId) {
        List<PlanHistory> planHistories = planHistoryService.findAllByGoalId(goalId);
        for (PlanHistory planHistory : planHistories) {
            planHistory.delete();
        }
        planHistoryService.saveAll(planHistories);
    }
}
