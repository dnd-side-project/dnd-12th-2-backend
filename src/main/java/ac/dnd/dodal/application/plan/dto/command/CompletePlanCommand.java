package ac.dnd.dodal.application.plan.dto.command;

import ac.dnd.dodal.domain.plan.enums.PlanStatus;

public record CompletePlanCommand(
    Long userId,
    Long planId,
    PlanStatus status,
    String question,
    String indicator
) {

    public CompletePlanCommand(
        Long userId, Long planId, PlanStatus status, String question, String indicator) {
        if (userId == null) {
            throw new IllegalArgumentException("CompletePlanCommand: userId is required");
        }
        if (planId == null) {
            throw new IllegalArgumentException("CompletePlanCommand: planId is required");
        }
        if (status == null) {
            throw new IllegalArgumentException("CompletePlanCommand: status is required");
        }
        if (status != PlanStatus.SUCCESS && status != PlanStatus.FAILURE) {
            throw new IllegalArgumentException(
                    "CompletePlanCommand: only SUCCESS or FAILURE status is allowed");
        }
        // TODO: MVP 배포 후 직접 입력 받는 것으로 수정
        // if (question == null || question.isEmpty()) {
        //     throw new IllegalArgumentException("CompletePlanCommand: question is required");
        // }
        // if (indicator == null || indicator.isEmpty()) {
        //     throw new IllegalArgumentException("CompletePlanCommand: indicator is required");
        // }
        this.userId = userId;
        this.planId = planId;
        this.status = status;
        this.question = question;
        this.indicator = indicator;
    }
}
