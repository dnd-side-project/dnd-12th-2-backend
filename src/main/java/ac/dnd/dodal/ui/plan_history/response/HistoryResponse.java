package ac.dnd.dodal.ui.plan_history.response;

public record HistoryResponse(
    Long historyId,
    String title,
    int successCount,
    int failureCount,
    int totalCount
) {
}
