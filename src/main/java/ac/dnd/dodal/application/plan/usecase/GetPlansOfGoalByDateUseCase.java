package ac.dnd.dodal.application.plan.usecase;

import java.util.List;

import ac.dnd.dodal.application.plan.dto.query.GetPlansOfGoalQuery;
import ac.dnd.dodal.ui.plan.response.PlanWithHistoryElement;

public interface GetPlansOfGoalByDateUseCase {

    List<PlanWithHistoryElement> getPlansOfGoalByDate(GetPlansOfGoalQuery query);
}
