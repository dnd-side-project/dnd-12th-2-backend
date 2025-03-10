package ac.dnd.dodal.ui.user_guide.response;

import ac.dnd.dodal.domain.guide.enums.GuideType;
import ac.dnd.dodal.domain.guide.model.UserGuide;

public record NewGoalAndPlanGuidesResponse(
    String newGoalGuide,
    String newPlanGuide
) {

    public static NewGoalAndPlanGuidesResponse of(UserGuide newGoalGuide, UserGuide newPlanGuide) {
        if (newGoalGuide.getType() != GuideType.NEW_GOAL 
                || newPlanGuide.getType() != GuideType.NEW_PLAN) {
            throw new IllegalArgumentException("Invalid guide type: " + newGoalGuide.getType()
                    + ", " + newPlanGuide.getType());
        }

        String tip = "Tip ";
        return new NewGoalAndPlanGuidesResponse(
            tip + newGoalGuide.getContent(), tip + newPlanGuide.getContent());
    }
}
