package ac.dnd.dodal.domain.goal.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import ac.dnd.dodal.domain.goal.model.Goal;

@Getter
@EqualsAndHashCode
public class GoalCreatedEvent {

    private Goal goal;

    public GoalCreatedEvent(Goal goal) {
        if (goal == null) {
            throw new IllegalArgumentException("GoalCreatedEvent: goal is null");
        }
        this.goal = goal;
    }
}
