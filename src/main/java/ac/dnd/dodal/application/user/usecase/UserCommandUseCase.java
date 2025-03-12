package ac.dnd.dodal.application.user.usecase;

import ac.dnd.dodal.domain.user.model.User;
import ac.dnd.dodal.ui.auth.request.OAuthUserInfoRequestDto;
import ac.dnd.dodal.ui.auth.response.DeleteUserInfoResponseDto;

public interface UserCommandUseCase {
    User createUserBySocialSignUp(OAuthUserInfoRequestDto authSignUpRequestDto);

    DeleteUserInfoResponseDto withdrawUser(Long userId);
}
