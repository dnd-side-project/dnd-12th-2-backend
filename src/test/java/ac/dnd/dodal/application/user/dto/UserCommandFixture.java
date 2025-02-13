package ac.dnd.dodal.application.user.dto;

import ac.dnd.dodal.domain.user.UserFixture;
import ac.dnd.dodal.ui.auth.request.OAuthUserInfoRequestDto;

public class UserCommandFixture {
    private static final String APPLE_ID = "appleId";

    public static OAuthUserInfoRequestDto signUpUser() {
        return UserFixture.createRequest();
    }

    public static OAuthUserInfoRequestDto signUpUserWithAppleId() {
        return UserFixture.createRequest(APPLE_ID);
    }
}
