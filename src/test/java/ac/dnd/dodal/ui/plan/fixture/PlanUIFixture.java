package ac.dnd.dodal.ui.plan.fixture;

import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.util.Set;

import ac.dnd.dodal.ui.plan.request.AddNewPlanRequest;
import ac.dnd.dodal.ui.plan.request.AddSamePlanRequest;
import ac.dnd.dodal.ui.plan.request.CreatePlanRequest;

public class PlanUIFixture {

    public static AddNewPlanRequest addNewPlanRequest() {
        return new AddNewPlanRequest("First plan with new history", LocalDateTime.now(),
                LocalDateTime.now().plusDays(1));
    }

    public static AddSamePlanRequest addSamePlanRequest() {
        return new AddSamePlanRequest(LocalDateTime.now(), LocalDateTime.now().plusDays(1),
                Set.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY), 5);
    }

    public static CreatePlanRequest createPlanRequest() {
        return new CreatePlanRequest("Plan with existing history",
                LocalDateTime.now(), LocalDateTime.now().plusDays(1));
    }
}
