package ac.dnd.dodal.domain.plan.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.EqualsAndHashCode;

import ac.dnd.dodal.domain.plan.enums.PlanStatus;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class DeletedPlanEvent {

    private final Long planId;
    private final Long historyId;
    private final PlanStatus status;
}
