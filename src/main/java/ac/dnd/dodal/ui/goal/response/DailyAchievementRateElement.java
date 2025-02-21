package ac.dnd.dodal.ui.goal.response;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

public record DailyAchievementRateElement(
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate date,
    int failureCount,
    int successCount,
    int totalCount
) {
    
}
