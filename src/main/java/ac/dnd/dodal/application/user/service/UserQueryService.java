package ac.dnd.dodal.application.user.service;

import ac.dnd.dodal.application.user.repository.UserRepository;
import ac.dnd.dodal.application.user.usecase.UserQueryUseCase;
import ac.dnd.dodal.domain.user.enums.UserExceptionCode;
import ac.dnd.dodal.domain.user.enums.UserRole;
import ac.dnd.dodal.domain.user.exception.UserBadRequestException;
import ac.dnd.dodal.domain.user.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserQueryService implements UserQueryUseCase {

    private final UserRepository userQueryRepository;

    @Override
    public User findByEmailAndRole(String email, UserRole role) {
        return userQueryRepository.findByEmailAndRole(email, role);
    }

    @Override
    public void checkDuplicatedEmail(String email) {
        if (userQueryRepository.existsByEmail(email)) {
            throw new UserBadRequestException(UserExceptionCode.DUPLICATED_EMAIL);
        }
    }

    @Override
    public void checkDuplicatedNickname(String nickname) {
        if (userQueryRepository.existsByNickname(nickname)) {
            throw new UserBadRequestException(UserExceptionCode.DUPLICATED_NICKNAME);
        }
    }

}
