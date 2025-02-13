package ac.dnd.dodal.ui.auth.request;


public record OAuthUserInfoRequestDto(
        String nickname, String email, String profileImageUrl, String appleId, String deviceToken
) {
//    public static OAuthUserInfoRequestDto of(String nickname, String email, String profileImageUrl, String appleId, String deviceToken) {
//        return new OAuthUserInfoRequestDto(nickname, email, profileImageUrl, appleId, deviceToken);
//    }
}
