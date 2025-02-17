package ac.dnd.dodal.application.plan_history.usecase;

import org.springframework.data.domain.Page;

import ac.dnd.dodal.application.plan_history.dto.query.GetHistoriesOfGoalQuery;
import ac.dnd.dodal.ui.plan_history.response.HistoryResponse;

public interface GetHistoriesOfGoalUseCase {

    Page<HistoryResponse> getHistoriesOfGoal(GetHistoriesOfGoalQuery query);
}
