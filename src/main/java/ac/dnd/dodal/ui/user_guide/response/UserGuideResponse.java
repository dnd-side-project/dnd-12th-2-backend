package ac.dnd.dodal.ui.user_guide.response;

import ac.dnd.dodal.domain.guide.enums.GuideType;
import ac.dnd.dodal.domain.guide.model.UserGuide;

public record UserGuideResponse(
    GuideType type,
    String guide
) {

    public static UserGuideResponse of(UserGuide userGuide) {
        return new UserGuideResponse(userGuide.getType(), userGuide.getContent());
    }
}
