package ac.dnd.dodal.application.plan_history.dto.query;

public record GetHistoryStatisticsQuery(
    Long userId,
    Long goalId,
    Long historyId
) {
}
