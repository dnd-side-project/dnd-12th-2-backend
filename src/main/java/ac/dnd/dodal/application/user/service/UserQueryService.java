package ac.dnd.dodal.application.user.service;

import ac.dnd.dodal.application.user.repository.UserAnswerRepository;
import ac.dnd.dodal.application.user.repository.UserRepository;
import ac.dnd.dodal.application.user.usecase.CheckIsDoneUserAnswerUseCase;
import ac.dnd.dodal.application.user.usecase.UserQueryUseCase;
import ac.dnd.dodal.domain.user.enums.UserExceptionCode;
import ac.dnd.dodal.domain.user.enums.UserRole;
import ac.dnd.dodal.domain.user.exception.UserBadRequestException;
import ac.dnd.dodal.domain.user.exception.UserNotFoundException;
import ac.dnd.dodal.domain.user.model.User;
import ac.dnd.dodal.domain.user.model.UserAnswer;
import ac.dnd.dodal.ui.user.response.GetUserAnswerResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserQueryService implements UserQueryUseCase, CheckIsDoneUserAnswerUseCase {

    private final UserRepository userQueryRepository;
    private final UserAnswerRepository userAnswerRepository;

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

    @Override
    public User findById(Long id) {
        return userQueryRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(UserExceptionCode.NOT_FOUND_USER));
    }

    @Override
    public GetUserAnswerResponseDto checkIsDoneUserAnswer(Long userId) {

        User user = userQueryRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(UserExceptionCode.NOT_FOUND_USER));

        List<UserAnswer> userAnswers =  userAnswerRepository.findAllByUser(user);

        if (userAnswers.isEmpty() || userAnswers == null) {
            return GetUserAnswerResponseDto.fromUserAnswersOnboardingNotDone();
        }

        return GetUserAnswerResponseDto.fromUserAnswersOnboardingDone(userAnswers);
    }
}
