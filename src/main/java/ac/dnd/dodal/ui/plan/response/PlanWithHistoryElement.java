package ac.dnd.dodal.ui.plan.response;

import java.util.List;

public record PlanWithHistoryElement(
    Long historyId,
    List<PlanElement> plans
) {
}
