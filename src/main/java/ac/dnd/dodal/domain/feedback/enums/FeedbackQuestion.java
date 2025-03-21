package ac.dnd.dodal.domain.feedback.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FeedbackQuestion {

    SUCCESS_PLAN_QUESTION("조금 더 수월하게 달성하기 위해 무엇을 바꿔볼까요?"),
    FAILURE_PLAN_QUESTION("조금 더 수월하게 달성하기 위해 무엇을 바꿔볼까요?"),
    ;

    private final String question;    
}
