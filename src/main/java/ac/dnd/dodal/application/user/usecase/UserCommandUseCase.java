package ac.dnd.dodal.application.user.usecase;

import ac.dnd.dodal.domain.user.model.User;
import ac.dnd.dodal.ui.auth.request.OAuthUserInfoRequestDto;

import java.time.LocalDateTime;

public interface UserCommandUseCase {
    User createUserBySocialSignUp(OAuthUserInfoRequestDto authSignUpRequestDto);

    LocalDateTime delete(Long userId);
}
