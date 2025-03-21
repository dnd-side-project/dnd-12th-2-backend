package ac.dnd.dodal.application.plan.usecase;

import java.util.List;

import ac.dnd.dodal.application.plan.dto.query.GetPlanQuery;
import ac.dnd.dodal.ui.plan.response.PlanElement;

public interface GetPlanUseCase {

    List<PlanElement> getPlanHistory(GetPlanQuery query);
}
