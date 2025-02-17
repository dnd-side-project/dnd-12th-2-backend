package ac.dnd.dodal.ui.auth.request;

public record OAuthByAppleUserInfoRequestDto(
        String appleId,
        String email,
        String nickname,
        String deviceToken
) {
    public static OAuthByAppleUserInfoRequestDto of(String oAuthId, String email, String nickname, String deviceToken) {
    return new OAuthByAppleUserInfoRequestDto(oAuthId, email, nickname, deviceToken);
    }
}
