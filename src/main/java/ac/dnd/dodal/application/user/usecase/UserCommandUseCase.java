package ac.dnd.dodal.application.user.usecase;

import ac.dnd.dodal.domain.user.model.User;
import ac.dnd.dodal.ui.auth.request.OAuthUserInfoRequestDto;
import ac.dnd.dodal.ui.user.request.WithdrawUserRequestDto;

public interface UserCommandUseCase {
    User createUserBySocialSignUp(OAuthUserInfoRequestDto authSignUpRequestDto);

    void withdrawUser(Long userId, WithdrawUserRequestDto withdrawUserRequestDto);
}
