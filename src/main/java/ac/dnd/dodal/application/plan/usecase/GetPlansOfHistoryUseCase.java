package ac.dnd.dodal.application.plan.usecase;

import org.springframework.data.domain.Page;

import ac.dnd.dodal.application.plan.dto.query.GetPlansOfHistoryQuery;
import ac.dnd.dodal.ui.plan.response.PlanElement;

public interface GetPlansOfHistoryUseCase {

    Page<PlanElement> getPlansOfHistory(GetPlansOfHistoryQuery query);
}
