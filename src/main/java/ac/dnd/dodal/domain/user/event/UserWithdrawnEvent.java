package ac.dnd.dodal.domain.user.event;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class UserWithdrawnEvent {
    private final Long userId;
}
