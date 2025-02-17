package ac.dnd.dodal.application.plan_history.usecase;

import ac.dnd.dodal.application.plan_history.dto.query.GetHistoryStatisticsQuery;
import ac.dnd.dodal.ui.plan_history.response.HistoryResponse;

public interface GetHistoryStatisticsUseCase {

    HistoryResponse getHistoryStatistics(GetHistoryStatisticsQuery query);
}
