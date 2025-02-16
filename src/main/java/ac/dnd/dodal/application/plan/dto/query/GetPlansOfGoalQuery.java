package ac.dnd.dodal.application.plan.dto.query;

import java.time.LocalDate;

public record GetPlansOfGoalQuery(
    Long userId,
    Long goalId,
    LocalDate date
) {
    
}
