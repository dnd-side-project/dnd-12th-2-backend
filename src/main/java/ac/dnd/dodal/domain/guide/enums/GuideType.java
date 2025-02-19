package ac.dnd.dodal.domain.guide.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GuideType {

    NEW_PLAN(NewPlanGuide.class),
    NEW_GOAL(NewGoalGuide.class),
    UPDATE_PLAN(UpdatePlanGuide.class),
    USER_TYPE(UserType.class),
    ;

    private final Class<? extends Enum<?>> guideClass;
}
