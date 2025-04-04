package ac.dnd.dodal.application.user.service;

import ac.dnd.dodal.application.onboarding.repository.AnswerRepository;
import ac.dnd.dodal.application.onboarding.repository.QuestionRepository;
import ac.dnd.dodal.application.user.repository.UserAnswerRepository;
import ac.dnd.dodal.application.user.repository.UserRepository;
import ac.dnd.dodal.application.user.usecase.CreateUserAnswerUseCase;
import ac.dnd.dodal.application.user.usecase.UserCommandUseCase;
import ac.dnd.dodal.common.exception.ForbiddenException;
import ac.dnd.dodal.common.util.OAuth2Util;
import ac.dnd.dodal.domain.onboarding.event.OnboardingProceededEvent;
import ac.dnd.dodal.domain.onboarding.exception.OnBoardingBadRequestException;
import ac.dnd.dodal.domain.onboarding.exception.OnBoardingExceptionCode;
import ac.dnd.dodal.domain.onboarding.exception.OnBoardingNotFoundException;
import ac.dnd.dodal.domain.onboarding.model.Answer;
import ac.dnd.dodal.domain.user.enums.UserExceptionCode;
import ac.dnd.dodal.domain.user.enums.UserRole;
import ac.dnd.dodal.domain.user.event.UserWithdrawnEvent;
import ac.dnd.dodal.domain.user.exception.UserNotFoundException;
import ac.dnd.dodal.domain.user.model.User;
import ac.dnd.dodal.domain.user.model.UserAnswer;
import ac.dnd.dodal.ui.auth.request.OAuthUserInfoRequestDto;
import ac.dnd.dodal.ui.user.request.CreateUserAnswerRequestDto;
import ac.dnd.dodal.ui.user.request.WithdrawUserRequestDto;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserCommandService implements UserCommandUseCase, CreateUserAnswerUseCase {

  private final UserRepository userCommandRepository;
  private final UserAnswerRepository userAnswerRepository;
  private final AnswerRepository answerRepository;
  private final QuestionRepository questionRepository;

  private final ApplicationEventPublisher eventPublisher;
  private final OAuth2Util oAuth2Util;

  @Override
  public User createUserBySocialSignUp(OAuthUserInfoRequestDto authSignUpRequestDto) {
    String appleUserId = authSignUpRequestDto.appleId();
    String email = authSignUpRequestDto.email();
    String profileImageUrl = authSignUpRequestDto.profileImageUrl();
    String nickname = authSignUpRequestDto.nickname();
    String deviceToken = authSignUpRequestDto.deviceToken();
    if (appleUserId != null) {
      return userCommandRepository.save(
          new User(nickname, profileImageUrl, deviceToken, appleUserId, UserRole.USER));
    }
    return userCommandRepository.save(
        new User(nickname, profileImageUrl, deviceToken, email, UserRole.USER));
  }

  @Override
  public void createUserAnswer(
      Long userId, CreateUserAnswerRequestDto createUserAnswerRequestDtoList) {
    // 들어온 데이터의 수가 전체 질문의 수와 일치하는 지 확인
    // 온보딩이 도중에 종료되었을 경우, 다시 처음부터 시작할 수 있도록 유도
    if (createUserAnswerRequestDtoList.data().size() != questionRepository.countAllQuestions()) {
      throw new OnBoardingBadRequestException(
          OnBoardingExceptionCode.INVALID_ANSWER_COUNT,
          "The number of answers does not match the total number of questions.");
    }

    // 사용자 조회
    User user =
        userCommandRepository
            .findById(userId)
            .orElseThrow(() -> new UserNotFoundException(UserExceptionCode.NOT_FOUND_USER));
    if (user.getDeletedAt() != null) {
      throw new ForbiddenException(UserExceptionCode.DELETED_USER);
    }

    // 사용자의 답변이 존재하는지 확인
    List<UserAnswer> existUserAnswer = userAnswerRepository.findAllByUserId(user);
    if (existUserAnswer != null && !existUserAnswer.isEmpty()) {
      throw new OnBoardingBadRequestException(OnBoardingExceptionCode.ALREADY_ANSWERED);
    }

    // 필요한 모든 questionId를 추출
    List<Long> questionIds =
        createUserAnswerRequestDtoList.data().stream()
            .map(CreateUserAnswerRequestDto.UserAnswerData::questionId)
            .distinct()
            .collect(Collectors.toList());

    // N+1을 피하기 위해 해당 questionId들을 가진 모든 Answer들을 미리 한 번에 조회
    List<Answer> answers = answerRepository.findAllByQuestionId(questionIds);

    // (questionId, answerId) 조합을 키로 하는 Map 생성
    Map<String, Answer> answerMap =
        answers.stream()
            .collect(
                Collectors.toMap(a -> a.getQuestion().getId() + "_" + a.getAnswerId(), a -> a));

    // Map에서 Answer를 찾아 UserAnswer 생성 및 저장
    List<UserAnswer> userAnswers = new ArrayList<>();
    for (CreateUserAnswerRequestDto.UserAnswerData dto : createUserAnswerRequestDtoList.data()) {
      String key = dto.questionId() + "_" + dto.answerId();
      Answer answer = answerMap.get(key);
      if (answer == null) {
        System.out.println("createUserAnswer answer = " + answer);
        throw new OnBoardingNotFoundException(
            OnBoardingExceptionCode.NOT_FOUND_ANSWER,
            "Answer ("
                + dto.answerId()
                + ") for question ("
                + dto.questionId()
                + ") does not exist.");
      }

      UserAnswer userAnswer =
          userAnswerRepository.save(
              new UserAnswer(
                  answer.getQuestion().getQuestionContentEnum().getMainContent(),
                  answer.getAnswerContentEnum(),
                  user,
                  1));
      userAnswers.add(userAnswer);
    }

    eventPublisher.publishEvent(new OnboardingProceededEvent(user.getId(), userAnswers));
  }

  @Override
  public void withdrawUser(Long userId, WithdrawUserRequestDto withdrawUserRequestDto) {
    User user =
        userCommandRepository
            .findById(userId)
            .orElseThrow(() -> new UserNotFoundException(UserExceptionCode.NOT_FOUND_USER));

    String appleAuthorizationCode = withdrawUserRequestDto.authorizationCode();

    String appleAccessToken = oAuth2Util.getAppleAccessToken(appleAuthorizationCode);

    oAuth2Util.revokeAppleToken(appleAccessToken);
    // 회원탈퇴 성공 시 User의 deletedAt을 현재 시간으로 업데이트하여 soft delete 처리
    user.withdrawUser();
    // 로그 추적을 위한 로그 출력
    log.info("User {} has been withdrawn.", user.getId());

    // TODO: 관련 데이터 비동기 삭제 로직 추가
    eventPublisher.publishEvent(new UserWithdrawnEvent(user.getId()));

  }
}
