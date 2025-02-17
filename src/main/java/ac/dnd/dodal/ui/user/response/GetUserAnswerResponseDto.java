package ac.dnd.dodal.ui.user.response;

import ac.dnd.dodal.domain.user.model.UserAnswer;

import java.util.List;
import java.util.stream.Collectors;

public record GetUserAnswerResponseDto(List<UserAnswerResponseData> data) {
  public record UserAnswerResponseData(String questionContent, String answerContent) {}

  // List<UserAnswer>를 DTO로 변환하는 정적 팩토리 메서드
  public static GetUserAnswerResponseDto fromUserAnswers(List<UserAnswer> userAnswers) {
    List<UserAnswerResponseData> data =
        userAnswers.stream()
            .map(
                ua ->
                    new UserAnswerResponseData(
                        // Question의 mainContent를 사용합니다.
                        ua.getQuestionContent(),
                        // Answer의 enum에서 실제 값(content)을 꺼내옵니다.
                        ua.getAnswerContent()))
            .collect(Collectors.toList());
    return new GetUserAnswerResponseDto(data);
  }
}
