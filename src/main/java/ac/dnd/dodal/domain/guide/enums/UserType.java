package ac.dnd.dodal.domain.guide.enums;

import java.util.List;
import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import ac.dnd.dodal.domain.onboarding.enums.AnswerContent;

@Getter
@RequiredArgsConstructor
public enum UserType {

    GOAL_ORIENTED("목표 중심형", List.of(
        List.of(AnswerContent.PREFERENCE_SET_PLAN_1, AnswerContent.DIFFICULTY_SET_PLAN_1),
        List.of(AnswerContent.PREFERENCE_SET_PLAN_4, AnswerContent.DIFFICULTY_SET_PLAN_1)
    )),
    LACK_OF_MOTIVATION_TO_EXECUTE("실행 동기 부족형", List.of(
        List.of(AnswerContent.PREFERENCE_SET_PLAN_1, AnswerContent.DIFFICULTY_SET_PLAN_3),
        List.of(AnswerContent.PREFERENCE_SET_PLAN_1, AnswerContent.DIFFICULTY_SET_PLAN_4)
    )),
    CHECKLIST_BASED("체크리스트 활용형", List.of(
        List.of(AnswerContent.PREFERENCE_SET_PLAN_1, AnswerContent.DIFFICULTY_SET_PLAN_2),
        List.of(AnswerContent.PREFERENCE_SET_PLAN_4, AnswerContent.DIFFICULTY_SET_PLAN_2),
        List.of(AnswerContent.PREFERENCE_SET_PLAN_4, AnswerContent.DIFFICULTY_SET_PLAN_3),
        List.of(AnswerContent.PREFERENCE_SET_PLAN_4, AnswerContent.DIFFICULTY_SET_PLAN_4)
    )),
    PREFERRING_A_BIG_DIRECTION("큰 방향성 선호형", List.of(
        List.of(AnswerContent.PREFERENCE_SET_PLAN_2, AnswerContent.DIFFICULTY_SET_PLAN_1),
        List.of(AnswerContent.PREFERENCE_SET_PLAN_2, AnswerContent.DIFFICULTY_SET_PLAN_2),
        List.of(AnswerContent.PREFERENCE_SET_PLAN_2, AnswerContent.DIFFICULTY_SET_PLAN_3),
        List.of(AnswerContent.PREFERENCE_SET_PLAN_2, AnswerContent.DIFFICULTY_SET_PLAN_4)
    )),
    FLEXIBLE_EXECUTION("유연한 실행형", List.of(
        List.of(AnswerContent.PREFERENCE_SET_PLAN_3, AnswerContent.DIFFICULTY_SET_PLAN_1),
        List.of(AnswerContent.PREFERENCE_SET_PLAN_3, AnswerContent.DIFFICULTY_SET_PLAN_2),
        List.of(AnswerContent.PREFERENCE_SET_PLAN_3, AnswerContent.DIFFICULTY_SET_PLAN_3),
        List.of(AnswerContent.PREFERENCE_SET_PLAN_3, AnswerContent.DIFFICULTY_SET_PLAN_4)
    ));

    private final String value;
    private final List<List<AnswerContent>> settingPlanAnswers;

    public static UserType getByPreferenceAndDifficultySetPlan
    (AnswerContent preferenceSetPlan, AnswerContent difficultySetPlan) {
        if (!AnswerContent.isPreferenceSetPlan(preferenceSetPlan)
                || !AnswerContent.isDifficultySetPlan(difficultySetPlan)) {
            throw new IllegalArgumentException(
                    "Invalid preference set plan or difficulty set plan");
        }

        List<AnswerContent> settingPlanAnswer = List.of(preferenceSetPlan, difficultySetPlan);

        return Arrays.stream(UserType.values())
            .filter(userType -> userType.getSettingPlanAnswers().contains(settingPlanAnswer))
            .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                    "Invalid preference set plan or difficulty set plan"));
    }

    public static UserType of(String value) {
        return Arrays.stream(UserType.values())
            .filter(userType -> userType.getValue().equals(value))
            .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                    "Invalid user type"));
    }
}
