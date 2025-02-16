package ac.dnd.dodal.ui.goal.response;

import java.time.LocalDate;

public record DailyAchievementRateElement(
    LocalDate date,
    int successCount,
    int totalCount
) {
    
}
