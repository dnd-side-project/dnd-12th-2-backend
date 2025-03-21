package ac.dnd.dodal.application.plan.dto;

import ac.dnd.dodal.domain.plan.enums.PlanStatus;
import ac.dnd.dodal.domain.feedback.enums.FeedbackQuestion;
import ac.dnd.dodal.domain.feedback.enums.FeedbackIndicator;
import ac.dnd.dodal.application.plan.dto.command.CompletePlanCommand;

public class CompletePlanCommandFixture {

    private static final Long userId = 1L;
    private static final Long planId = 1L;
    private static final String question = FeedbackQuestion.SUCCESS_PLAN_QUESTION.getQuestion();
    private static final String indicator = FeedbackIndicator.REORGANIZE_PRIORITIES.getIndicator();

    public static CompletePlanCommand completePlanCommand() {
        return new CompletePlanCommand(userId, planId, PlanStatus.SUCCESS, question, indicator);
    }

    public static CompletePlanCommand successPlanCommand() {
        return new CompletePlanCommand(userId, planId, PlanStatus.SUCCESS, question, indicator);
    }

    public static CompletePlanCommand failurePlanCommand() {
        return new CompletePlanCommand(userId, planId, PlanStatus.FAILURE, question, indicator);
    }

    public static CompletePlanCommand nonePlanCommand() {
        return new CompletePlanCommand(userId, planId, PlanStatus.NONE, question, indicator);
    }
}
