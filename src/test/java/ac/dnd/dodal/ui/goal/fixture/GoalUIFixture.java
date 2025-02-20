package ac.dnd.dodal.ui.goal.fixture;

import java.time.LocalDateTime;

import ac.dnd.dodal.ui.goal.request.CreateGoalRequest;
import ac.dnd.dodal.ui.goal.request.CreateGoalAndPlanRequest;

public class GoalUIFixture {

    public static CreateGoalAndPlanRequest createGoalAndPlanRequest() {
        return new CreateGoalAndPlanRequest(
            "test goal", "test plan", LocalDateTime.now(), LocalDateTime.now().plusDays(1));
    }

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
