package ac.dnd.dodal.domain.plan;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ac.dnd.dodal.domain.goal.model.Goal;
import ac.dnd.dodal.domain.plan.enums.PlanStatus;
import ac.dnd.dodal.domain.plan.model.Plan;
import ac.dnd.dodal.domain.goal.GoalFixture;
import ac.dnd.dodal.domain.plan_history.model.PlanHistory;
import ac.dnd.dodal.domain.plan_history.PlanHistoryFixture;

public class PlanFixture {

    private static final Long PLAN_ID = 1L;
    private static final Goal GOAL = GoalFixture.goal();
    private static final PlanHistory HISTORY = PlanHistoryFixture.planHistory();

    public static Plan plan() {
        return new Plan(PLAN_ID, GOAL, HISTORY,
            "title", PlanStatus.NONE, null,
            LocalDateTime.now(), LocalDateTime.now().plusDays(1), null,
            LocalDateTime.now(), LocalDateTime.now(), null);
    }

    public static Plan plan(String title, PlanStatus status, String guide,
            LocalDateTime startDate, LocalDateTime endDate, LocalDateTime completedDate) {
        return new Plan(PLAN_ID, GOAL, HISTORY, title, status, guide, startDate, endDate,
            completedDate, LocalDateTime.now(), LocalDateTime.now(), null);
    }

    public static Plan deletedPlan() {
        return new Plan(PLAN_ID, GOAL, HISTORY,
            "title", PlanStatus.NONE, null,
            LocalDateTime.now(), LocalDateTime.now().plusDays(1), null,
            LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
    }

    public static Plan deletedPlan(String title, PlanStatus status, String guide,
            LocalDateTime startDate, LocalDateTime endDate, LocalDateTime completedDate) {
        return new Plan(PLAN_ID, GOAL, HISTORY,
            title, status, guide, startDate, endDate, completedDate,
            LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
    }

    public static Plan successPlan() {
        return new Plan(PLAN_ID, GOAL, HISTORY,
            "title", PlanStatus.SUCCESS, "guide",
            LocalDateTime.now(), LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1),
            LocalDateTime.now(), LocalDateTime.now(), null);
    }

    public static Plan successPlan(String title, String guide,
            LocalDateTime startDate, LocalDateTime endDate, LocalDateTime completedDate) {
        return new Plan(PLAN_ID, GOAL, HISTORY,
            title, PlanStatus.SUCCESS, guide, startDate, endDate, completedDate,
            LocalDateTime.now(), LocalDateTime.now(), null);
    }

    public static Plan failurePlan() {
        return new Plan(PLAN_ID, GOAL, HISTORY, "title", PlanStatus.FAILURE, "guide",
                LocalDateTime.now(), LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1),
                LocalDateTime.now(), LocalDateTime.now(), null);
    }

    public static Plan failurePlan(String title, String guide,
            LocalDateTime startDate, LocalDateTime endDate, LocalDateTime completedDate) {
        return new Plan(PLAN_ID, GOAL, HISTORY,
            title, PlanStatus.FAILURE, guide, startDate, endDate, completedDate,
            LocalDateTime.now(), LocalDateTime.now(), null);
    }

    public static Plan tommorowStartPlan() {
        return new Plan(PLAN_ID, GOAL, HISTORY, "title", PlanStatus.NONE, null,
            LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2), null,
            LocalDateTime.now(), LocalDateTime.now(), null);
    }

    public static List<Plan> monthlyPlan() {
        return Arrays.asList(
            successPlan("success plan", "guide",
                LocalDateTime.now().minusDays(10), LocalDateTime.now().minusDays(9), LocalDateTime.now().minusDays(9)),
            successPlan("success plan", "guide", LocalDateTime.now().minusDays(9),
                LocalDateTime.now().minusDays(8), LocalDateTime.now().minusDays(8)),
            successPlan("success plan", "guide", LocalDateTime.now().minusDays(8),
                LocalDateTime.now().minusDays(7), LocalDateTime.now().minusDays(7)),
            successPlan("success plan", "guide", LocalDateTime.now().minusDays(7),
                LocalDateTime.now().minusDays(6), LocalDateTime.now().minusDays(6)),
            successPlan("success plan", "guide", LocalDateTime.now().minusDays(6),
                LocalDateTime.now().minusDays(5), LocalDateTime.now().minusDays(5)),
            successPlan("success plan", "guide", LocalDateTime.now().minusDays(5),
                LocalDateTime.now().minusDays(4), LocalDateTime.now().minusDays(4)),
            successPlan("success plan", "guide", LocalDateTime.now().minusDays(4),
                LocalDateTime.now().minusDays(3), LocalDateTime.now().minusDays(3)),
            successPlan("success plan", "guide", LocalDateTime.now().minusDays(3),
                LocalDateTime.now().minusDays(2), LocalDateTime.now().minusDays(2)),
            successPlan("success plan", "guide", LocalDateTime.now().minusDays(2),
                LocalDateTime.now().minusDays(1), LocalDateTime.now().minusDays(1)),
            successPlan("success plan", "guide", LocalDateTime.now().minusDays(1),
                LocalDateTime.now(), LocalDateTime.now()),
            plan("plan", PlanStatus.NONE, null,
                LocalDateTime.now(), LocalDateTime.now().plusDays(1), null),
            plan("plan", PlanStatus.NONE, null,
                LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2), null),
            plan("plan", PlanStatus.NONE, null,
                LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(3), null),
            plan("plan", PlanStatus.NONE, null,
                LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(4), null),
            plan("plan", PlanStatus.NONE, null,
                LocalDateTime.now().plusDays(4), LocalDateTime.now().plusDays(5), null));
    }

    public static List<Plan> plans() {
        List<Plan> plans = new ArrayList<>();
        plans.add(PlanFixture.plan());
        plans.add(PlanFixture.plan());
        plans.add(PlanFixture.plan());
        plans.add(PlanFixture.plan());
        plans.add(PlanFixture.plan());
        plans.add(PlanFixture.plan());
        plans.add(PlanFixture.plan());
        plans.add(PlanFixture.plan());
        return plans;
    }

    public static List<Plan> lastSuccessPlans() {
        List<Plan> plans = new ArrayList<>();
        plans.add(PlanFixture.successPlan());
        plans.add(PlanFixture.failurePlan());
        plans.add(PlanFixture.successPlan());
        return plans;
    }

    public static List<Plan> lastFailedPlans() {
        List<Plan> plans = new ArrayList<>();
        plans.add(PlanFixture.failurePlan());
        plans.add(PlanFixture.failurePlan());
        plans.add(PlanFixture.failurePlan());
        return plans;
    }
}
