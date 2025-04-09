package ac.dnd.dodal.domain.goal.event;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class DeletedGoalEvent {
    private Long goalId;
}
