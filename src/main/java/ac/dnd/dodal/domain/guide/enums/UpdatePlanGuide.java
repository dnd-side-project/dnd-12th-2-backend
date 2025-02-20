package ac.dnd.dodal.domain.guide.enums;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UpdatePlanGuide {

    // SUCCESS
    REORGANIZE_PRIORITIES("가장 중요한 목표부터 다시 정리해볼까요?",
        UserType.GOAL_ORIENTED, "우선 순위를 재조정해요."),
    SIMPLIFY_CORE_STEPS("핵심 단계만 남기고 단순화해보세요.",
        UserType.GOAL_ORIENTED, "시간 단축을 시도해볼래요."),
    ALIGN_GOAL_AND_PLAN("목표와 계획이 잘 맞는지 살펴보세요.",
        UserType.GOAL_ORIENTED, "계획 정확도를 점검해요."),
    REVIEW_PROGRESS("현재까지의 진행 상태를 점검해보세요.",
        UserType.GOAL_ORIENTED, "진행 상황을 더 꼼꼼히 확인해요."),
    LEARN_FROM_PAST("지금까지의 방식에서 배울 점을 찾아보세요.", 
        UserType.GOAL_ORIENTED, "스스로 패턴을 분석해봐요."),

    ADJUST_PRIORITIES("지금 상황에 맞게 우선 순위를 조정해볼까요?",
        UserType.FLEXIBLE_EXECUTION, "우선 순위를 재조정해요."),
    TRY_TIME_SAVING_METHODS("시간을 줄일 방법을 가볍게 시도해보세요.",
        UserType.FLEXIBLE_EXECUTION, "시간 단축을 시도해볼래요."),
    ADJUST_WHILE_EXECUTING("계획보다는 실행하면서 조정해보세요.",
        UserType.FLEXIBLE_EXECUTION, "계획 실행 시간을 좀 더 확보해요."),
    MODIFY_DURING_PROCESS("진행하면서 필요한 조정을 해보세요.",
        UserType.FLEXIBLE_EXECUTION, "진행 상황을 더 꼼꼼히 확인해요."),
    IDENTIFY_EFFECTIVE_TIME("내가 가장 효과적으로 움직일 때를 생각해보세요.",
        UserType.FLEXIBLE_EXECUTION, "스스로 패턴을 분석해봐요."),

    REORGANIZE_CHECKLIST("체크리스트를 다시 정리해볼까요?",
        UserType.CHECKLIST_BASED, "우선 순위를 재조정해요."),
    REMOVE_UNNECESSARY_STEPS("불필요한 단계를 줄여볼까요?",
        UserType.CHECKLIST_BASED, "시간 단축을 시도해볼래요."),
    FINE_TUNE_PLAN("계획을 더 세밀하게 조정해볼까요?",
        UserType.CHECKLIST_BASED, "계획 정확도를 점검해요."),
    TRACK_PROGRESS_WITH_CHECKLIST("체크리스트를 보며 진행 상황을 확인해보세요.",
        UserType.CHECKLIST_BASED, "진행 상황을 더 꼼꼼히 확인해요."),
    DISCOVER_PATTERN_THROUGH_RECORDS("기록을 통해 나만의 패턴을 찾아보세요.",
        UserType.CHECKLIST_BASED, "스스로 패턴을 분석해봐요."),

    RECONSIDER_MAIN_GOALS("중요한 목표부터 다시 생각해볼까요?",
        UserType.PREFERRING_A_BIG_DIRECTION, "우선 순위를 재조정해요."),
    BALANCE_BIG_PICTURE_AND_TIME("큰 그림을 유지하며 시간을 조정해보세요.",
        UserType.PREFERRING_A_BIG_DIRECTION, "시간 단축을 시도해볼래요."),
    VERIFY_GOAL_ALIGNMENT("목표와 맞는 계획인지 다시 확인해보세요.",
        UserType.PREFERRING_A_BIG_DIRECTION, "계획 정확도를 점검해요."),
    CHECK_CURRENT_POSITION("목표를 떠올리며 현재 위치를 점검해보세요.",
        UserType.PREFERRING_A_BIG_DIRECTION, "진행 상황을 더 꼼꼼히 확인해요."),
    ANALYZE_PATTERNS_IN_FLOW("큰 흐름 속에서 나의 패턴을 살펴보세요.",
        UserType.PREFERRING_A_BIG_DIRECTION, "스스로 패턴을 분석해봐요."),

    START_WITH_EASY_TASKS_SUCCESS("쉬운 것부터 시작해볼까요?",
        UserType.LACK_OF_MOTIVATION_TO_EXECUTE, "우선 순위를 재조정해요."),
    QUICKLY_COMPLETE_SMALL_GOALS("작은 목표부터 빠르게 해보세요.",
        UserType.LACK_OF_MOTIVATION_TO_EXECUTE, "시간 단축을 시도해볼래요."),
    ADJUST_FOR_MANAGEABILITY("너무 어렵지 않게 계획을 조정해보세요.",
        UserType.LACK_OF_MOTIVATION_TO_EXECUTE, "계획 실행 시간을 좀 더 확보해요."),
    MOTIVATE_THROUGH_SMALL_SUCCESS("조금씩 해낸 걸 확인하며 동기를 얻어보세요.",
        UserType.LACK_OF_MOTIVATION_TO_EXECUTE, "진행 상황을 더 꼼꼼히 확인해요."),
    FIND_COMFORTABLE_START_METHOD("내가 편하게 시작할 수 있는 방법을 찾아보세요.",
        UserType.LACK_OF_MOTIVATION_TO_EXECUTE, "스스로 패턴을 분석해봐요."),

    // FAILURE
    SIMPLIFY_STEPS("단계를 줄여 더 간단하게 시작해볼까요?",
        UserType.GOAL_ORIENTED, "계획을 더 쉽게 설정해요."),
    BREAK_DOWN_GOALS("목표를 더 구체적인 단계로 나눠볼까요?",
        UserType.GOAL_ORIENTED, "구체적인 계획을 설정해요."),
    CHECK_PROGRESS_PERIODICALLY("중간중간 진행을 체크해보세요.",
        UserType.GOAL_ORIENTED, "진행상황을 수시로 확인해요."),
    ALLOCATE_TIME_FOR_IMPORTANT_GOALS("중요한 목표를 위해 시간을 먼저 확보해보세요.",
        UserType.GOAL_ORIENTED, "계획 실행 시간을 좀 더 확보해요."),
    CREATE_FOCUSED_ENVIRONMENT("더 집중할 수 있는 환경을 만들어볼까요?",
        UserType.GOAL_ORIENTED, "주변 환경을 개선해요."),

    START_WITH_EASY_TASKS_FAILURE("먼저 할 수 있는 쉬운 것부터 해볼까요?",
        UserType.FLEXIBLE_EXECUTION, "계획을 더 쉽게 설정해요."),
    PLAN_SIMPLY("너무 복잡하지 않게 가볍게 계획해보세요.",
        UserType.FLEXIBLE_EXECUTION, "구체적인 계획을 설정해요."),
    ADJUST_DURING_PROGRESS("진행하면서 필요한 조정을 해보세요.",
        UserType.FLEXIBLE_EXECUTION, "진행 상황을 수시로 확인해요."),
    FIND_OPTIMAL_EXECUTION_TIME("실행이 편한 시간대를 찾아보세요.",
        UserType.FLEXIBLE_EXECUTION, "계획 실행 시간을 좀 더 확보해요."),
    CREATE_COMFORTABLE_ENVIRONMENT("내가 편하게 몰입할 수 있는 환경을 만들어보세요.",
        UserType.FLEXIBLE_EXECUTION, "주변 환경을 개선해요."),

    SIMPLIFY_CHECKLIST("체크리스트를 더 단순하게 만들어볼까요?",
        UserType.CHECKLIST_BASED, "계획을 더 쉽게 설정해요."),
    DETAIL_SUBSTEPS("세부 단계를 더 구체적으로 정리해볼까요?",
        UserType.CHECKLIST_BASED, "구체적인 계획을 설정해요."),
    TRACK_PROGRESS_WITH_LIST("리스트를 보며 진행 상태를 점검해보세요.",
        UserType.CHECKLIST_BASED, "진행 상황을 더 꼼꼼히 확인해요."),
    RESERVE_TIME_IN_SCHEDULE("일정에 맞춰 시간을 미리 확보해보세요.",
        UserType.CHECKLIST_BASED, "계획 실행 시간을 좀 더 확보해요."),
    WORK_IN_ORGANIZED_SPACE("정리된 공간에서 더 효율적으로 진행해보세요.",
        UserType.CHECKLIST_BASED, "주변 환경을 개선해요."),

    OUTLINE_MAIN_FLOW("큰 흐름만 정리하고 시작해볼까요?",
        UserType.PREFERRING_A_BIG_DIRECTION, "계획을 더 쉽게 설정해요."),
    REFINE_GOALS("목표를 더 구체적인 방향으로 정리해볼까요?",
        UserType.PREFERRING_A_BIG_DIRECTION, "구체적인 계획을 설정해요."),
    CHECK_DIRECTION_OCCASIONALLY("방향이 맞는지 가끔 점검해보세요.",
        UserType.PREFERRING_A_BIG_DIRECTION, "진행 상황을 수시로 확인해요."),
    ADJUST_TIME_FOR_GOALS("목표를 위해 시간을 어떻게 조정할지 고민해보세요.",
        UserType.PREFERRING_A_BIG_DIRECTION, "계획 실행 시간을 좀 더 확보해요."),
    CREATE_INSPIRING_ENVIRONMENT("나에게 영감을 주는 환경을 만들어볼까요?",
        UserType.PREFERRING_A_BIG_DIRECTION, "주변 환경을 개선해요."),

    START_EASILY("너무 어렵지 않게 쉽게 시작해볼까요?",
        UserType.LACK_OF_MOTIVATION_TO_EXECUTE, "계획을 더 쉽게 설정해요."),
    SET_SMALL_ACHIEVABLE_GOALS("실천하기 편한 작은 목표부터 정해볼까요?",
        UserType.LACK_OF_MOTIVATION_TO_EXECUTE, "구체적인 계획을 설정해요."),
    RECORD_SMALL_SUCCESSES("작은 성취를 기록하며 동기를 만들어보세요.",
        UserType.LACK_OF_MOTIVATION_TO_EXECUTE, "진행 상황을 수시로 확인해요."),
    SCHEDULE_EXECUTION_TIME("실행할 시간을 미리 정해두면 도움이 될 거예요.",
        UserType.LACK_OF_MOTIVATION_TO_EXECUTE, "계획 실행 시간을 좀 더 확보해요."),
    MAKE_STARTING_EASIER("더 편하게 시작할 수 있는 환경을 만들어보세요.",
        UserType.LACK_OF_MOTIVATION_TO_EXECUTE, "주변 환경을 개선해요.");
    ;

    private final String guide;
    private final UserType userType;
    private final String feedbackIndicator;

    public static UpdatePlanGuide getByUserTypeAndFeedbackIndicator
        (String userType, String feedbackIndicator) {
        UserType userTypeEnum = UserType.valueOf(userType);

        return Arrays.stream(UpdatePlanGuide.values())
            .filter(guide -> guide.getUserType().equals(userTypeEnum) 
                && guide.getFeedbackIndicator().equals(feedbackIndicator))
            .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                "Invalid user type or feedback indicator: " + userType + ", " + feedbackIndicator));
    }
}
