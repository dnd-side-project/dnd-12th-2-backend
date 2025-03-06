package ac.dnd.dodal.application.plan.usecase;

import java.util.List;

import ac.dnd.dodal.application.plan.dto.query.GetPlansOfGoalQuery;
import ac.dnd.dodal.ui.plan.response.PlanElement;

public interface GetPlansOfGoalByDateUseCase {

    List<PlanElement> getPlansOfGoalByDate(GetPlansOfGoalQuery query);
}
