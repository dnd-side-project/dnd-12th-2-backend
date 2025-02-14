package ac.dnd.dodal.application.plan.usecase;

import org.springframework.data.domain.Page;
import ac.dnd.dodal.ui.plan.response.PlanElement;

public interface GetPlansOfHistoryUseCase {

    Page<PlanElement> getPlansOfHistory(Long userId, Long goalId, Long historyId);
}
