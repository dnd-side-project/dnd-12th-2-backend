package ac.dnd.dodal.ui.plan.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public record PlanWithHistoryElement(
    Long historyId,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    PlanElement previousPlan,
    PlanElement plan
) {
}
