package ac.dnd.dodal.ui.feedback.request;

import ac.dnd.dodal.domain.plan.enums.PlanStatus;
import ac.dnd.dodal.application.plan.dto.command.CompletePlanCommand;

public record CreateFeedbackRequest(
    String question,
    String indicator
) {

    public CompletePlanCommand toCommand(Long userId, Long planId, PlanStatus status) {
        return new CompletePlanCommand(userId, planId, status, question, indicator);
    }

    public CreateFeedbackRequest(String question, String indicator) {
        // TODO: MVP 배포 후 직접 입력 받는 것으로 수정
        // if (question == null || question.isEmpty()) {
        //     throw new IllegalArgumentException("Question cannot be null or empty");
        // }
        // if (indicator == null || indicator.isEmpty()) {
        //     throw new IllegalArgumentException("Indicator cannot be null or empty");
        // }
        this.question = question;
        this.indicator = indicator;
    }
}
