package ac.dnd.dodal.application.plan.dto;

import ac.dnd.dodal.domain.plan.enums.PlanStatus;
import ac.dnd.dodal.application.plan.dto.command.CompletePlanCommand;

public class CompletePlanCommandFixture {

    private static final Long userId = 1L;
    private static final Long planId = 1L;
    private static final String question = "조금 더 수월하게 달성하기 위해 무엇을 바꿔볼까요?";
    private static final String indicator = "우선순위를 재조정해요.";

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
