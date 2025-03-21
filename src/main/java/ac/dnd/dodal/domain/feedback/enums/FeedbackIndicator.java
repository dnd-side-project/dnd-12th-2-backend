package ac.dnd.dodal.domain.feedback.enums;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FeedbackIndicator {

    // SUCCESS
    REORGANIZE_PRIORITIES(FeedbackType.SUCCESS, "우선 순위를 재조정해요."),
    TRY_TIME_SAVING(FeedbackType.SUCCESS, "시간 단축을 시도해볼래요."),
    CHECK_PLAN_ACCURACY(FeedbackType.SUCCESS, "계획 정확도를 점검해요."),
    CHECK_PROGRESS(FeedbackType.SUCCESS, "진행 상황을 더 꼼꼼히 확인해요."),
    ANALYZE_PATTERN(FeedbackType.SUCCESS, "스스로 패턴을 분석해봐요."),

    // FAILURE
    SIMPLIFY_STEPS(FeedbackType.FAILURE, "계획을 더 쉽게 설정해요."),
    SPECIFY_PLAN(FeedbackType.FAILURE, "구체적인 계획을 설정해요."),
    CHECK_PROGRESS_REGULARLY(FeedbackType.FAILURE, "진행상황을 수시로 확인해요."),
    ALLOCATE_MORE_TIME(FeedbackType.FAILURE, "계획 실행 시간을 좀 더 확보해요."),
    IMPROVE_ENVIRONMENT(FeedbackType.FAILURE, "주변 환경을 개선해요."),
    ;

    private final FeedbackType type;
    private final String indicator;

    public static FeedbackIndicator of(String indicator) {
        return Arrays.stream(FeedbackIndicator.values())
            .filter(feedbackIndicator -> feedbackIndicator.getIndicator().equals(indicator))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Invalid feedback indicator: " + indicator));
    }
}
