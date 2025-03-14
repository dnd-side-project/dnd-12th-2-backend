package ac.dnd.dodal.application.user.usecase;

import ac.dnd.dodal.domain.user.model.User;
import ac.dnd.dodal.ui.auth.request.OAuthUserInfoRequestDto;

public interface UserCommandUseCase {
    User createUserBySocialSignUp(OAuthUserInfoRequestDto authSignUpRequestDto);

    void withdrawUser(Long userId);
}
