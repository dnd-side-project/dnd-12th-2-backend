package ac.dnd.dodal.application.user.service;

import ac.dnd.dodal.application.user.repository.UserRepository;
import ac.dnd.dodal.application.user.usecase.UserQueryUseCase;
import ac.dnd.dodal.domain.user.enums.UserExceptionCode;
import ac.dnd.dodal.domain.user.enums.UserRole;
import ac.dnd.dodal.domain.user.exception.UserBadRequestException;
import ac.dnd.dodal.domain.user.exception.UserNotFoundException;
import ac.dnd.dodal.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public Optional<User> findByEmail(String email) {
        return userQueryRepository.findByEmail(email);
    }

    @Override
    public void checkDuplicatedEmail(String email) {
        if (userQueryRepository.existsByEmail(email)) {
            throw new UserBadRequestException(UserExceptionCode.DUPLICATED_EMAIL);
        }
    }

    @Override
    public User findByIdAndRole(Long id, UserRole userRole) {
        return userQueryRepository.findByIdAndRefreshTokenNotNull(id)
                .orElseThrow(() -> new UserNotFoundException(UserExceptionCode.NOT_FOUND_USER));
    }

}
