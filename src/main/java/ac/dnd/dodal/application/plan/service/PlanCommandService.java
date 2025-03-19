package ac.dnd.dodal.application.plan.service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ac.dnd.dodal.application.plan_history.service.HistoryStatisticsService;
import ac.dnd.dodal.domain.plan_history.model.HistoryStatistics;
import ac.dnd.dodal.ui.plan.response.PlanElement;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.ApplicationEventPublisher;

import ac.dnd.dodal.common.exception.ForbiddenException;
import ac.dnd.dodal.domain.plan.exception.PlanExceptionCode;
import ac.dnd.dodal.domain.goal.model.Goal;
import ac.dnd.dodal.domain.guide.enums.GuideType;
import ac.dnd.dodal.domain.guide.enums.UserType;
import ac.dnd.dodal.domain.guide.model.UserGuide;
import ac.dnd.dodal.domain.guide.util.GuidianceGenerator;
import ac.dnd.dodal.domain.plan_history.model.PlanHistory;
import ac.dnd.dodal.domain.plan.model.Plan;
import ac.dnd.dodal.domain.plan.event.PlanCompletedEvent;
import ac.dnd.dodal.domain.plan.event.DeletedPlanEvent;
import ac.dnd.dodal.domain.plan_feedback.model.PlanFeedback;
import ac.dnd.dodal.application.goal.service.GoalService;
import ac.dnd.dodal.application.plan.dto.command.*;
import ac.dnd.dodal.application.plan.usecase.*;
import ac.dnd.dodal.application.plan_feedback.service.PlanFeedbackService;
import ac.dnd.dodal.application.plan_history.service.PlanHistoryService;
import ac.dnd.dodal.application.user_guide.service.UserGuideService;

@Transactional
@Service
@RequiredArgsConstructor
public class PlanCommandService implements 
    AddPlanUseCase, CreatePlanAndHistoryUseCase, CompletePlanUseCase, DeletePlanUseCase {

    private final GoalService goalService;
    private final PlanHistoryService planHistoryService;
    private final PlanService planService;
    private final PlanFeedbackService planFeedbackService;
    private final UserGuideService userGuideService;
    private final HistoryStatisticsService historyStatisticsService;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void addSamePlan(AddSamePlanCommand command) {
        Goal goal = goalService.findByIdOrThrow(command.goalId());
        Plan previousPlan = planService.findByIdOrThrow(command.planId());
        PlanHistory planHistory = previousPlan.getHistory();
        Plan latestPlan = planService.findLatestPlanByHistoryId(planHistory.getHistoryId())
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
        Plan previousPlan = planService.findByIdOrThrow(command.planId());
        PlanHistory planHistory = previousPlan.getHistory();
        Plan latestPlan = planService.findLatestPlanByHistoryId(planHistory.getHistoryId())
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
        HistoryStatistics historyStatistics = new HistoryStatistics(planHistory.getHistoryId());
        historyStatisticsService.save(historyStatistics);

        planHistory.addPlan(plan, null, null);
        goal.addPlan(command.userId(), plan);
        planService.save(plan);
    }

    // TODO: MVP 배포 후 직접 입력 받는 것으로 수정
    @Override
    public List<PlanElement> completePlan(CompletePlanCommand command) {
        Plan plan = planService.findByIdOrThrow(command.planId());
        List<PlanFeedback> feedbacks = new ArrayList<>();
//         PlanFeedback feedback = new PlanFeedback(command.question(), command.indicator());
        PlanFeedback feedback = new PlanFeedback("조금 더 수월하게 달성하기 위해 무엇을 바꿔볼까요?", "우선 순위를 재조정해요.");
        feedbacks.add(feedback);
        UserGuide userTypeGuide =
                userGuideService.findByUserIdAndTypeOrThrow(command.userId(), GuideType.USER_TYPE);
        UserType userType = UserType.of(userTypeGuide.getContent());
        String guide =
                GuidianceGenerator.generateUpdatePlanGuide(userType, feedback.getIndicator());
 
        plan.getGoal().completePlan(command.userId(), command.status(), plan, feedbacks, guide);

        eventPublisher.publishEvent(PlanCompletedEvent.of(plan, feedback));
        planFeedbackService.saveAll(feedbacks);
        userGuideService.updateUpdatePlanGuide(command.userId(), guide);
        planService.save(plan);

        // 가장 오래된 계획과 개선할 점 을 두 개와 방금 완료한 계획 출력
        return planService.findOlderAndNowByHistoryIdOrThrow(plan.getHistory().getHistoryId(), plan.getPlanId());
    }
    
    @Override
    public void delete(DeletePlanCommand command) {
        Plan plan = planService.findByIdOrThrow(command.planId());

        // TODO: 현재 plan crud에서 user에 대한 검증이 없음. getGoal로 수행하는 것은 매우 비효율적 -> 로직 개선 필수
        if (!plan.getGoal().getUserId().equals(command.userId())) {
            throw new ForbiddenException(PlanExceptionCode.PLAN_NOT_FOUND);
        }
        plan.delete();
        planService.save(plan);
        eventPublisher.publishEvent(
                new DeletedPlanEvent(
                        plan.getPlanId(),
                        plan.getHistory().getHistoryId(), 
                        command.userId(), 
                        plan.getStatus()));
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
