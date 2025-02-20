package ac.dnd.dodal.domain.onboarding.enums;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AnswerContent {

    INTEREST_GOAL_1("건강/운동"),
    INTEREST_GOAL_2("학업/자기개발"),
    INTEREST_GOAL_3("커리어/직업"),
    INTEREST_GOAL_4("취미/여가"),
    PREFERENCE_SET_PLAN_1("구체적인 단계별 계획"),
    PREFERENCE_SET_PLAN_2("큰 방향성만 잡기"),
    PREFERENCE_SET_PLAN_3("유연하게 접근하기"),
    PREFERENCE_SET_PLAN_4("체크리스트 활용"),
    DIFFICULTY_SET_PLAN_1("목표 구체화"),
    DIFFICULTY_SET_PLAN_2("시간 관리"),
    DIFFICULTY_SET_PLAN_3("우선순위 결정"),
    DIFFICULTY_SET_PLAN_4("실행 동기 부족"),
    ;

    private final String content;

    public static AnswerContent of(String content) {
        return Arrays.stream(AnswerContent.values())
            .filter(answerContent -> answerContent.getContent().equals(content))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Invalid answer content: " + content));
    }

    public static boolean isInterestGoal(AnswerContent answerContent) {
      return INTEREST_GOAL_1.equals(answerContent)
          || INTEREST_GOAL_2.equals(answerContent)
          || INTEREST_GOAL_3.equals(answerContent) 
          || INTEREST_GOAL_4.equals(answerContent);
    }

    public static boolean isPreferenceSetPlan(AnswerContent answerContent) {
        return PREFERENCE_SET_PLAN_1.equals(answerContent)
            || PREFERENCE_SET_PLAN_2.equals(answerContent)
            || PREFERENCE_SET_PLAN_3.equals(answerContent)
            || PREFERENCE_SET_PLAN_4.equals(answerContent);
    }

    public static boolean isDifficultySetPlan(AnswerContent answerContent) {
        return DIFFICULTY_SET_PLAN_1.equals(answerContent)
            || DIFFICULTY_SET_PLAN_2.equals(answerContent)
            || DIFFICULTY_SET_PLAN_3.equals(answerContent)
            || DIFFICULTY_SET_PLAN_4.equals(answerContent);
    }
}
