package ac.dnd.dodal.application.plan_history.dto.query;

import org.springframework.data.domain.Pageable;

public record GetHistoriesOfGoalQuery(
    Long userId,
    Long goalId,
    Pageable pageable
) {
}
