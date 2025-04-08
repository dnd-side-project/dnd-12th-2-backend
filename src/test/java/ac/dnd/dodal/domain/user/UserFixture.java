package ac.dnd.dodal.domain.user;

import ac.dnd.dodal.domain.user.enums.UserRole;
import ac.dnd.dodal.domain.user.model.User;
import ac.dnd.dodal.ui.auth.request.OAuthUserInfoRequestDto;

public class UserFixture {

  public static final Long USER_ID = 1L;
  private static final String EMAIL = "testEmail@test.example";
  private static final String NICKNAME = "testUser";
  private static final String profileImage = "testProfileImageURL";
  private static final String deviceToken = "testDeviceToken";

  public static OAuthUserInfoRequestDto createRequest() {
    return new OAuthUserInfoRequestDto(NICKNAME, EMAIL, profileImage, null, deviceToken);
  }

  public static OAuthUserInfoRequestDto createRequest(String appleId) {
    return new OAuthUserInfoRequestDto(NICKNAME, EMAIL, profileImage, appleId, deviceToken);
  }

  public static User createUser() {
    return new User(NICKNAME, profileImage, deviceToken, EMAIL, UserRole.USER);
  }
}
