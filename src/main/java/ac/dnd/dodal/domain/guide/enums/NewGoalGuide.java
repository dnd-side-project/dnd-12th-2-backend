package ac.dnd.dodal.domain.guide.enums;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import ac.dnd.dodal.domain.onboarding.enums.AnswerContent;

@RequiredArgsConstructor
@Getter
public enum NewGoalGuide {

    SPECIFIC_GOAL(
        "구체적이고 달성 가능한 목표로 설정해보세요. 예: \"3개월 동안 체지방 3% 감량\"",
        AnswerContent.INTEREST_GOAL_1
    ),
    MEASURABLE_GOAL(
        "진행 상황을 측정할 수 있도록 목표를 세워보세요. 예: \"한 달 동안 영어책 1권 완독\"",
        AnswerContent.INTEREST_GOAL_2
    ),
    DIVIDE_GOAL(
        "장기 목표를 작은 단계로 나누어 보세요. 예: \"6개월 안에 포트폴리오 3개 완성\"",
        AnswerContent.INTEREST_GOAL_3
    ),
    CONNECT_SCHEDULE(
        "일정과 연계해 꾸준히 실천할 수 있는 목표로 정해보세요. 예: \"매주 일요일, 2시간씩 드로잉 연습\"",
        AnswerContent.INTEREST_GOAL_4
    ),
    ;

    private final String guide;
    private final AnswerContent interestGoal;

    public static NewGoalGuide getByInterestGoal(AnswerContent interestGoal) {
        if (!AnswerContent.isInterestGoal(interestGoal)) {
            throw new IllegalArgumentException("Invalid interest goal: " + interestGoal);
        }

        return Arrays.stream(NewGoalGuide.values())
                .filter(guide -> guide.getInterestGoal() == interestGoal)
                .findFirst()
                .orElseThrow(
                        () -> 
                new IllegalArgumentException("Invalid interest goal: " + interestGoal));
    }
}
