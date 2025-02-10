package ac.dnd.dodal.ui.goal.fixture;

import ac.dnd.dodal.ui.goal.request.GoalCreateRequest;

public class GoalUIFixture {

    public static GoalCreateRequest createGoalRequest(String title) {
        return new GoalCreateRequest(title);
    }

    public static GoalCreateRequest createBlackGoalRequest() {
        return new GoalCreateRequest("       ");
    }

    public static GoalCreateRequest createEmptyGoalRequest() {
        return new GoalCreateRequest("");
    }

    public static GoalCreateRequest createNullGoalRequest() {
        return new GoalCreateRequest(null);
    }

    public static GoalCreateRequest createExceedTitleGoalRequest() {
        return new GoalCreateRequest("a".repeat(21));
    }

    // TODO: 목표 시각화 fixture 추가
}
