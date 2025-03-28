package ac.dnd.dodal.domain.user;

import ac.dnd.dodal.domain.user.enums.UserRole;
import ac.dnd.dodal.domain.user.model.User;
import ac.dnd.dodal.ui.auth.request.OAuthUserInfoRequestDto;

public class UserFixture {

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

  public static User createUser(String email, String nickname, String profileImageUrl, String deviceToken) {
    return new User("testUser", "testUserProfileImageUrl", "testDeviceToken", "testEmail@test.example", UserRole.USER);
  }
}
