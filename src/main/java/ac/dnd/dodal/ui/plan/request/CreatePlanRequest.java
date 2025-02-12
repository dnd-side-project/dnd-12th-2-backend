package ac.dnd.dodal.ui.plan.request;

import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.util.Set;

public record CreatePlanRequest(

    String title,
    LocalDateTime startDate,
    LocalDateTime endDate,
    Set<DayOfWeek> iterationDays,
    int iterationCount
) {

    // TODO: CreatePlanCommand 변환 로직 구현
}
