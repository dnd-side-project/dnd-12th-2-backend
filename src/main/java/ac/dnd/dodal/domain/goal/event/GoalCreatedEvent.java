package ac.dnd.dodal.domain.goal.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class GoalCreatedEvent {

    private Long goalId;

    public GoalCreatedEvent(Long goalId) {
        if (goalId == null) {
            throw new IllegalArgumentException("GoalCreatedEvent: goalId is null");
        }
        this.goalId = goalId;
    }
}
