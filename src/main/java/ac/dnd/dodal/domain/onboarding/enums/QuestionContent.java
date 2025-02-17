package ac.dnd.dodal.domain.onboarding.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum QuestionContent {

  INTEREST_GOAL("현재 가장 관심 있는 목표 분야를 선택해 주세요.", "분야별로 최적의 인사이트를 추천해드릴게요!"),
  PREFERENCE_SET_PLAN("계획을 세울 때 선호하는 방식을 골라주세요.", "당신의 스타일에 딱 맞는 실행 전략을 찾아드릴게요!"),
  DIFFICULTY_SET_PLAN("그동안 계획 설정 시 어려웠던 점을 선택해 주세요.", "당신의 고민에 맞춘 계획 개선 팁을 제공해드릴 수 있어요!"),
  ;

  private final String mainContent;
  private final String subContent;
}
