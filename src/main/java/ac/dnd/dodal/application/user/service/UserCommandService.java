package ac.dnd.dodal.application.user.service;

import ac.dnd.dodal.application.onboarding.repository.AnswerRepository;
import ac.dnd.dodal.application.user.repository.UserAnswerRepository;
import ac.dnd.dodal.application.user.repository.UserRepository;
import ac.dnd.dodal.application.user.usecase.CreateUserAnswerUseCase;
import ac.dnd.dodal.application.user.usecase.UserCommandUseCase;
import ac.dnd.dodal.domain.onboarding.exception.AnswerExceptionCode;
import ac.dnd.dodal.domain.onboarding.exception.AnswerNotFoundException;
import ac.dnd.dodal.domain.onboarding.model.Answer;
import ac.dnd.dodal.domain.user.enums.UserExceptionCode;
import ac.dnd.dodal.domain.user.enums.UserRole;
import ac.dnd.dodal.domain.user.exception.UserNotFoundException;
import ac.dnd.dodal.domain.user.model.User;
import ac.dnd.dodal.domain.user.model.UserAnswer;
import ac.dnd.dodal.ui.auth.request.OAuthUserInfoRequestDto;
import ac.dnd.dodal.ui.user.request.CreateUserAnswerRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserCommandService implements UserCommandUseCase, CreateUserAnswerUseCase {

    private final UserRepository userCommandRepository;
    private final UserAnswerRepository userAnswerRepository;
    private final AnswerRepository answerRepository;

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

    @Override
    public void createUserAnswer(Long userId, CreateUserAnswerRequestDto createUserAnswerRequestDtoList) {
        // 사용자 조회
        User user = userCommandRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(UserExceptionCode.NOT_FOUND_USER));

        // 사용자의 답변이 존재하는지 확인
        List<UserAnswer> existUserAnswer = userAnswerRepository.findAllByUserId(user);
        if (existUserAnswer != null && !existUserAnswer.isEmpty()) {
            log.info("existUserAnswer: {}", existUserAnswer);
            userAnswerRepository.deleteAll();
        }

        // 필요한 모든 questionId를 추출
        List<Long> questionIds = createUserAnswerRequestDtoList.data().stream()
                .map(CreateUserAnswerRequestDto.UserAnswerData::questionId)
                .distinct()
                .collect(Collectors.toList());

        // N+1을 피하기 위해 해당 questionId들을 가진 모든 Answer들을 미리 한 번에 조회
        List<Answer> answers = answerRepository.findAllByQuestionId(questionIds);

        // (questionId, answerId) 조합을 키로 하는 Map 생성
        Map<String, Answer> answerMap = answers.stream()
                .collect(Collectors.toMap(
                        a -> a.getQuestion().getId() + "_" + a.getAnswerId(),
                        a -> a
                ));

        // Map에서 Answer를 찾아 UserAnswer 생성 및 저장
        for (CreateUserAnswerRequestDto.UserAnswerData dto : createUserAnswerRequestDtoList.data()) {
            String key = dto.questionId() + "_" + dto.answerId();
            Answer answer = answerMap.get(key);
            if (answer == null) {
                throw new AnswerNotFoundException(AnswerExceptionCode.NOT_FOUND_ANSWER,
                        "Answer (" + dto.answerId() + ") for question (" + dto.questionId() + ") does not exist.");
            }

            userAnswerRepository.save(new UserAnswer(answer, user));
        }
    }
}
