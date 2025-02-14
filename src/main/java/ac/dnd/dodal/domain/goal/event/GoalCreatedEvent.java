package ac.dnd.dodal.domain.goal.event;

import lombok.Getter;

@Getter
public class GoalCreatedEvent {

    private Long goalId;

    public GoalCreatedEvent(Long goalId) {
        if (goalId == null) {
            throw new IllegalArgumentException("GoalCreatedEvent: goalId is null");
        }
        this.goalId = goalId;
    }
}
