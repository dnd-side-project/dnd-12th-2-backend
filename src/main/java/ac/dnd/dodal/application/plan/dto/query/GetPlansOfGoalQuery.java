package ac.dnd.dodal.application.plan.dto.query;

import java.time.LocalDate;

public record GetPlansOfGoalQuery(
    Long userId,
    Long goalId,
    LocalDate date,
    int range
) {
    
    public GetPlansOfGoalQuery {
        if (userId == null) {
            throw new IllegalArgumentException("GetPlansOfGoalQuery: User ID is required");
        }
        if (goalId == null) {
            throw new IllegalArgumentException("GetPlansOfGoalQuery: Goal ID is required");
        }
        if (date == null) {
            throw new IllegalArgumentException("GetPlansOfGoalQuery: Date is required");
        }
        if (range < 0) {
            throw new IllegalArgumentException(
                "GetPlansOfGoalQuery: Range must be greater than 0");
        }
    }
}
