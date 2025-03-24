package ac.dnd.dodal.ui.user.request;

public record WithdrawUserRequestDto(
        String deviceToken,
        String authorizationCode
) {}
