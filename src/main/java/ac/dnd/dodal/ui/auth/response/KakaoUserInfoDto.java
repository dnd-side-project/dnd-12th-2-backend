package ac.dnd.dodal.ui.auth.response;

public record KakaoUserInfoDto(String email, String nickname, String profileImageUrl) {

  public static KakaoUserInfoDto of(String email, String nickname, String profileImageUrl) {
    return new KakaoUserInfoDto(email, nickname, profileImageUrl);
  }
}
