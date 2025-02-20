package ac.dnd.dodal.application.user_guide.usecase;

import ac.dnd.dodal.domain.guide.enums.GuideType;
import ac.dnd.dodal.ui.user_guide.response.UserGuideResponse;
import ac.dnd.dodal.ui.user_guide.response.NewGoalAndPlanGuidesResponse;

public interface GetUserGuideUseCase {

    UserGuideResponse getUserGuide(Long userId, GuideType guideType);

    NewGoalAndPlanGuidesResponse getNewGoalAndPlanGuides(Long userId);
}
