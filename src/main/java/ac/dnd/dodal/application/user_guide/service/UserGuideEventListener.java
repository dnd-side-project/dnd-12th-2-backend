package ac.dnd.dodal.application.user_guide.service;

import java.util.ArrayList;
import java.util.List;

import ac.dnd.dodal.application.user_guide.dto.command.DeleteAllUserGuideCommand;
import ac.dnd.dodal.domain.user.event.UserWithdrawnEvent;
import lombok.RequiredArgsConstructor;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import ac.dnd.dodal.domain.onboarding.event.OnboardingProceededEvent;
import ac.dnd.dodal.domain.guide.model.UserGuide;
import ac.dnd.dodal.domain.guide.enums.GuideType;
import ac.dnd.dodal.domain.guide.util.GuidianceGenerator;

@Component
@RequiredArgsConstructor
public class UserGuideEventListener {
    
    private final UserGuideService userGuideService;

    @EventListener
    public void onOnboardingProceededEvent(OnboardingProceededEvent event) {
        List<UserGuide> userGuides = new ArrayList<>();
        UserGuide userType = new UserGuide(event.getUserId(), GuideType.USER_TYPE,
                GuidianceGenerator.generateUserTypeGuide(event.getUserAnswers()));
        UserGuide newGoalGuide = new UserGuide(event.getUserId(), GuideType.NEW_GOAL,
                GuidianceGenerator.generateNewGoalGuide(event.getUserAnswers()));
        UserGuide newPlanGuide = new UserGuide(event.getUserId(), GuideType.NEW_PLAN,
                GuidianceGenerator.generateNewPlanGuide(event.getUserAnswers()));
        UserGuide updatePlanGuide = new UserGuide(event.getUserId(), GuideType.UPDATE_PLAN,
                "피드백을 제출해주세요! 적합한 가이드를 제공해드립니다.");
        userGuides.add(userType);
        userGuides.add(newGoalGuide);
        userGuides.add(newPlanGuide);
        userGuides.add(updatePlanGuide);
        userGuideService.saveAll(userGuides);
    }

    @EventListener
    public void onUserWithdrawnEvent(UserWithdrawnEvent event) {
        userGuideService.deleteAll(new DeleteAllUserGuideCommand(event.getUserId()));
    }
}
