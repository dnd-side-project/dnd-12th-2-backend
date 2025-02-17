package ac.dnd.dodal.application.plan.dto.query;

import org.springframework.data.domain.Pageable;
public record GetPlansOfHistoryQuery(
    Long userId,
    Long goalId,
    Long historyId,
    Pageable pageable
) {
    
}
