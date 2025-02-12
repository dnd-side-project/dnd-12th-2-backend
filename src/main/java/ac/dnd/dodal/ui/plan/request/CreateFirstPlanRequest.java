package ac.dnd.dodal.ui.plan.request;

import java.time.LocalDateTime;

public record CreateFirstPlanRequest(

    String title,
    LocalDateTime startDate,
    LocalDateTime endDate
) {

    // TODO: CreatePlanCommand 변환 로직 구현
}
