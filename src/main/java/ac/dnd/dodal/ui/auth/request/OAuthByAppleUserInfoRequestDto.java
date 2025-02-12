package ac.dnd.dodal.ui.auth.request;

public record OAuthByAppleUserInfoRequestDto(
        String appleId,
        String email,
        String nickname,
        Long userId
) {
    public static OAuthByAppleUserInfoRequestDto of(String oAuthId, String email, String nickname, Long userId) {
    return new OAuthByAppleUserInfoRequestDto(oAuthId, email, nickname, userId);
    }
}
