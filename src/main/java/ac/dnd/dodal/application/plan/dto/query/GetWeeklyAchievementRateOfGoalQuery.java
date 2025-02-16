package ac.dnd.dodal.application.plan.dto.query;

import java.time.LocalDate;

public record GetWeeklyAchievementRateOfGoalQuery(
    Long userId,
    Long goalId,
    LocalDate date
) {
    
}
