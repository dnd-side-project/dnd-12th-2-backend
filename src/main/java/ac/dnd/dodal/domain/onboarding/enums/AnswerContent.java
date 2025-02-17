package ac.dnd.dodal.domain.onboarding.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AnswerContent {

  INTEREST_GOAL_1("건강/운동"),
  INTEREST_GOAL_2("학습/자기계발"),
  INTEREST_GOAL_3("커리어/직업"),
  INTEREST_GOAL_4("취미/여가"),
  PREFERENCE_SET_PLAN_1("구체적인 단위별 계획"),
  PREFERENCE_SET_PLAN_2("큰 방향성만 잡기"),
  PREFERENCE_SET_PLAN_3("유연하게 접근하기"),
  PREFERENCE_SET_PLAN_4("체크리스트 활용"),
  DIFFICULTY_SET_PLAN_1("목표 구체화"),
  DIFFICULTY_SET_PLAN_2("시간 관리"),
  DIFFICULTY_SET_PLAN_3("우선순위 결정"),
  DIFFICULTY_SET_PLAN_4("실행 동기 부족"),
  ;

  private final String content;
}
