package ac.dnd.dodal.ui.user_guide.response;

import ac.dnd.dodal.domain.guide.model.UserGuide;

public record UserGuideResponse(
    String type,
    String guide
) {

    public static UserGuideResponse of(UserGuide userGuide) {
        String tip = "Tip ";
        return new UserGuideResponse(
            tip + userGuide.getType().getValue(), tip + userGuide.getContent());
    }
}
