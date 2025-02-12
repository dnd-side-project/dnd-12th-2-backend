package ac.dnd.dodal.ui.user.request;

public record AppleSignUpRequestDto(
        String code,
        String deviceToken
) {}
