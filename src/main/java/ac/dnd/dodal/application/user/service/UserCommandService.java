package ac.dnd.dodal.application.user.service;

import ac.dnd.dodal.application.user.repository.UserRepository;
import ac.dnd.dodal.application.user.usecase.UserCommandUseCase;
import ac.dnd.dodal.domain.user.enums.UserRole;
import ac.dnd.dodal.domain.user.model.User;
import ac.dnd.dodal.ui.auth.request.OAuthUserInfoRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserCommandService implements UserCommandUseCase {

    private final UserRepository userCommandRepository;

    @Override
    public User createUserBySocialSignUp(OAuthUserInfoRequestDto authSignUpRequestDto) {
        String appleUserId = authSignUpRequestDto.appleId();
        String email = authSignUpRequestDto.email();
        String profileImageUrl = authSignUpRequestDto.profileImageUrl();
        String nickname = authSignUpRequestDto.nickname();
        String deviceToken = authSignUpRequestDto.deviceToken();
        if (appleUserId != null) {
      return userCommandRepository.save(new User(nickname, profileImageUrl, deviceToken, appleUserId, UserRole.USER));
        }
    return userCommandRepository.save(new User(nickname, profileImageUrl,deviceToken, email, UserRole.USER));
    }
}
