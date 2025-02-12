package ac.dnd.dodal.ui.plan.fixture;

import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.util.Set;

import ac.dnd.dodal.ui.plan.request.CreateFirstPlanRequest;
import ac.dnd.dodal.ui.plan.request.CreatePlanRequest;

public class PlanUIFixture {

    public static CreateFirstPlanRequest createFirstPlanRequest() {
        return new CreateFirstPlanRequest("First plan with new history",
                LocalDateTime.now(), LocalDateTime.now().plusDays(1));
    }

    public static CreatePlanRequest createPlanRequest() {
        return new CreatePlanRequest("Plan with existing history",
                LocalDateTime.now(), LocalDateTime.now().plusDays(1),
                Set.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY), 5);
    }
}
