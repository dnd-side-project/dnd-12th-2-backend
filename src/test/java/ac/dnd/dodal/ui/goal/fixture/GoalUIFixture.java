package ac.dnd.dodal.ui.goal.fixture;

import ac.dnd.dodal.ui.goal.request.CreateGoalRequest;

public class GoalUIFixture {

    public static CreateGoalRequest createGoalRequest(String title) {
        return new CreateGoalRequest(title);
    }

    public static CreateGoalRequest createBlackGoalRequest() {
        return new CreateGoalRequest("       ");
    }

    public static CreateGoalRequest createEmptyGoalRequest() {
        return new CreateGoalRequest("");
    }

    public static CreateGoalRequest createNullGoalRequest() {
        return new CreateGoalRequest(null);
    }

    public static CreateGoalRequest createExceedTitleGoalRequest() {
        return new CreateGoalRequest("a".repeat(21));
    }

    // TODO: 목표 시각화 fixture 추가
}
