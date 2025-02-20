package ac.dnd.dodal.domain.guide.enums;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import ac.dnd.dodal.domain.onboarding.enums.AnswerContent;

@Getter
@RequiredArgsConstructor
public enum NewPlanGuide {

    BREAK_DOWN_GOAL("목표를 더 작게 나눠볼까요?",
        AnswerContent.PREFERENCE_SET_PLAN_1, AnswerContent.DIFFICULTY_SET_PLAN_1),
    SET_FIXED_TIME("정해진 시간에 실천해볼까요?",
        AnswerContent.PREFERENCE_SET_PLAN_1, AnswerContent.DIFFICULTY_SET_PLAN_2),
    PRIORITIZE_TASKS("가장 중요한 것부터 해볼까요?",
        AnswerContent.PREFERENCE_SET_PLAN_1, AnswerContent.DIFFICULTY_SET_PLAN_3),
    VISUALIZE_RESULT("실천 후 결과를 떠올려보세요!",
        AnswerContent.PREFERENCE_SET_PLAN_1, AnswerContent.DIFFICULTY_SET_PLAN_4),

    DIVIDE_INTO_STEPS("큰 방향을 작은 단계로 나눠볼까요?",
        AnswerContent.PREFERENCE_SET_PLAN_2, AnswerContent.DIFFICULTY_SET_PLAN_1),
    SET_TIME_FRAME("시간을 정하면 더 구체화돼요!",
        AnswerContent.PREFERENCE_SET_PLAN_2, AnswerContent.DIFFICULTY_SET_PLAN_2),
    IDENTIFY_IMPORTANCE("무엇이 중요한지 먼저 정리해볼까요?",
        AnswerContent.PREFERENCE_SET_PLAN_2, AnswerContent.DIFFICULTY_SET_PLAN_3),
    USE_VISUALS("목표를 시각적으로 정리해볼까요?",
        AnswerContent.PREFERENCE_SET_PLAN_2, AnswerContent.DIFFICULTY_SET_PLAN_4),

    ADD_DETAILED_SUBGOALS("큰 틀을 유지하며 세부 목표를 추가해볼까요?",
        AnswerContent.PREFERENCE_SET_PLAN_3, AnswerContent.DIFFICULTY_SET_PLAN_1),
    REALISTIC_ROUTINE("완벽한 계획보다 현실적인 루틴이 중요해요.",
        AnswerContent.PREFERENCE_SET_PLAN_3, AnswerContent.DIFFICULTY_SET_PLAN_2),
    START_SIMPLE("가장 쉽게 시작할 수 있는 것부터 해볼까요?",
        AnswerContent.PREFERENCE_SET_PLAN_3, AnswerContent.DIFFICULTY_SET_PLAN_3),
    BUILD_SMALL_SUCCESSES("작은 성공을 쌓아볼까요?",
        AnswerContent.PREFERENCE_SET_PLAN_3, AnswerContent.DIFFICULTY_SET_PLAN_4),

    SPLIT_INTO_SMALL_UNITS("계획을 작은 단위로 나누면 더 쉽게 실천할 수 있어요!",
        AnswerContent.PREFERENCE_SET_PLAN_4, AnswerContent.DIFFICULTY_SET_PLAN_1),
    USE_CHECKLIST("체크리스트를 하루 또는 주 단위로 나눠볼까요?",
        AnswerContent.PREFERENCE_SET_PLAN_4, AnswerContent.DIFFICULTY_SET_PLAN_2),
    DIFFERENTIATE_PRIORITY("빠르게 끝낼 것과 중요한 것을 구분해볼까요?",
        AnswerContent.PREFERENCE_SET_PLAN_4, AnswerContent.DIFFICULTY_SET_PLAN_3),
    ACCUMULATE_ACHIEVEMENTS("하나씩 완료하면 성취감이 쌓일 거예요!",
        AnswerContent.PREFERENCE_SET_PLAN_4, AnswerContent.DIFFICULTY_SET_PLAN_4),
    ;

    private final String guide;
    private final AnswerContent preferenceSetPlan;
    private final AnswerContent difficultySetPlan;

    public static NewPlanGuide getByPreferenceAndDifficultySetPlan(
        AnswerContent preferenceSetPlan, AnswerContent difficultySetPlan) {
        if (!AnswerContent.isPreferenceSetPlan(preferenceSetPlan) 
            || !AnswerContent.isDifficultySetPlan(difficultySetPlan)) {
            throw new IllegalArgumentException("Invalid preference set plan or difficulty set plan");
        }

        return Arrays.stream(NewPlanGuide.values())
            .filter(guide -> guide.getPreferenceSetPlan().equals(preferenceSetPlan)
                && guide.getDifficultySetPlan().equals(difficultySetPlan))
            .findFirst()
            .orElseThrow(() -> 
                new IllegalArgumentException("Invalid preference set plan or difficulty set plan"));
    }
}
