package ac.dnd.dodal.ui.auth.request;

public record AppleSignUpRequestDto(
        String code,
        String deviceToken
) {}
