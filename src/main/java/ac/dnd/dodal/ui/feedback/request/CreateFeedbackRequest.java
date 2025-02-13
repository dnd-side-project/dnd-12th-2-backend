package ac.dnd.dodal.ui.feedback.request;

import ac.dnd.dodal.domain.plan.enums.PlanStatus;
import ac.dnd.dodal.application.plan.dto.command.CompletePlanCommand;

public record CreateFeedbackRequest(
    String question,
    String indicator
) {

    public CompletePlanCommand toCommand(Long planId, PlanStatus status) {
        return new CompletePlanCommand(planId, status, question, indicator);
    }

    public CreateFeedbackRequest(String question, String indicator) {
        if (question == null || question.isEmpty()) {
            throw new IllegalArgumentException("Question cannot be null or empty");
        }
        if (indicator == null || indicator.isEmpty()) {
            throw new IllegalArgumentException("Indicator cannot be null or empty");
        }
        this.question = question;
        this.indicator = indicator;
    }
}
