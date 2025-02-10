package ac.dnd.dodal.ui.user.response;

public record KakaoUserInfoDto(String email) {
  public static KakaoUserInfoDto of(String email) {
    return new KakaoUserInfoDto(email);
  }
}
