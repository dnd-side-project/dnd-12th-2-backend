package ac.dnd.dodal.application.plan.service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ac.dnd.dodal.domain.goal.model.Goal;
import ac.dnd.dodal.domain.plan_history.model.PlanHistory;
import ac.dnd.dodal.domain.plan.model.Plan;
import ac.dnd.dodal.domain.plan_feedback.model.PlanFeedback;
import ac.dnd.dodal.application.goal.service.GoalService;
import ac.dnd.dodal.application.goal.usecase.AddPlanUseCase;
import ac.dnd.dodal.application.goal.usecase.CreatePlanAndHistoryUseCase;
import ac.dnd.dodal.application.plan.dto.command.*;
import ac.dnd.dodal.application.plan.usecase.CompletePlanUseCase;
import ac.dnd.dodal.application.plan_history.service.PlanHistoryService;
import ac.dnd.dodal.application.feedback.service.PlanFeedbackService;

@Transactional
@Service
@RequiredArgsConstructor
public class PlanCommandService implements 
    AddPlanUseCase, CreatePlanAndHistoryUseCase, CompletePlanUseCase {

    private final GoalService goalService;
    private final PlanHistoryService planHistoryService;
    private final PlanService planService;
    private final PlanFeedbackService planFeedbackService;

    @Override
    public void addSamePlan(AddSamePlanCommand command) {
        Goal goal = goalService.findByIdOrThrow(command.goalId());
        PlanHistory planHistory = planHistoryService.findByIdOrThrow(command.planHistoryId());
        Plan previousPlan = planService.findByIdOrThrow(command.planId());
        Plan latestPlan = planService.findLatestPlanByHistoryId(command.planHistoryId())
                .orElse(null);
        List<Plan> plans = generateIterationPlans(command, previousPlan);

        plans.forEach(plan -> {
            goal.addPlan(command.userId(), plan);
            planHistory.addPlan(plan, previousPlan, latestPlan);
        });
        planService.saveAll(plans);
    }

    @Override
    public void addNewPlan(AddNewPlanCommand command) {
        Goal goal = goalService.findByIdOrThrow(command.goalId());
        PlanHistory planHistory = planHistoryService.findByIdOrThrow(command.planHistoryId());
        Plan previousPlan = planService.findByIdOrThrow(command.planId());
        Plan latestPlan = planService.findLatestPlanByHistoryId(command.planHistoryId())
                .orElse(null);
        Plan plan = new Plan(command.title(), command.startDate(), command.endDate());

        planHistory.addPlan(plan, previousPlan, latestPlan);
        goal.addPlan(command.userId(), plan);
        planService.save(plan);
    }

    @Override
    public void createPlanAndHistory(CreatePlanAndHistoryCommand command) {
        Goal goal = goalService.findByIdOrThrow(command.goalId());
        PlanHistory planHistory = new PlanHistory();
        Plan plan = new Plan(command.title(), command.startDate(), command.endDate());

        goal.addHistory(command.userId(), planHistory);
        planHistoryService.saveAndFlush(planHistory);

        planHistory.addPlan(plan, null, null);
        goal.addPlan(command.userId(), plan);
        planService.save(plan);
    }

    @Override
    public Plan completePlan(CompletePlanCommand command) {
        Plan plan = planService.findByIdOrThrow(command.planId());
        List<PlanFeedback> feedbacks = new ArrayList<>();
        PlanFeedback feedback = new PlanFeedback(command.question(), command.indicator());
        feedbacks.add(feedback);

        plan.getGoal().completePlan(command.userId(), command.status(), plan, feedbacks);
        planFeedbackService.saveAll(feedbacks);
        return planService.save(plan);
    }

    private List<Plan> generateIterationPlans(AddSamePlanCommand command, Plan plan) {
        List<Plan> plans = new ArrayList<>();
        LocalDateTime startDate;
        LocalDateTime endDate;
        Set<DayOfWeek> days;
        int iterationCount = command.iterationCount();
        int count = 0;
        Long gap;

        if (command.days() == null || command.days().isEmpty()) {
            days = Set.of(DayOfWeek.values());
        } else {
            days = command.days();
        }
        if (command.iterationCount() == null) {
            iterationCount = 1;
        }
        if (command.startDate() == null || command.endDate() == null) {
            startDate = plan.getStartDate().plusDays(1);
            endDate = plan.getEndDate().plusDays(1);
        } else {
            startDate = command.startDate();
            endDate = command.endDate();
        }
        gap = getGap(startDate, endDate);

        for (LocalDateTime date = startDate;
                count < iterationCount; date = date.plusDays(1)) {
            if (isDayOfWeek(date, days)) {
                plans.add(new Plan(plan.getTitle(), date, date.plusMinutes(gap)));
                count++;
            }
        }
        return plans;
    }

    private static boolean isDayOfWeek(LocalDateTime date, Set<DayOfWeek> days) {
        return days.contains(date.getDayOfWeek());
    }

    private static Long getGap(LocalDateTime startDate, LocalDateTime endDate) {
        return Duration.between(startDate, endDate).toMinutes();
    }
}
