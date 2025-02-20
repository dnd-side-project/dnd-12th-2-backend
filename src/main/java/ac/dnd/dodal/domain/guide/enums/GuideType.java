package ac.dnd.dodal.domain.guide.enums;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GuideType {

    NEW_PLAN("new-plan", NewPlanGuide.class),
    NEW_GOAL("new-goal", NewGoalGuide.class),
    UPDATE_PLAN("update-plan", UpdatePlanGuide.class),
    USER_TYPE("user-type", UserType.class),
    ;

    private final String value;
    private final Class<? extends Enum<?>> guideClass;

    public static GuideType of(String value) {
        return Arrays.stream(GuideType.values())
            .filter(type -> type.getValue().equals(value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Invalid guide type: " + value));
    }
}
