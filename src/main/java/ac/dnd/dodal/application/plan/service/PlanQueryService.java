package ac.dnd.dodal.application.plan.service;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import ac.dnd.dodal.common.exception.ForbiddenException;
import ac.dnd.dodal.domain.plan.enums.PlanStatus;
import ac.dnd.dodal.application.plan.repository.PlanRepository;
import ac.dnd.dodal.application.plan.usecase.GetPlansOfHistoryUseCase;
import ac.dnd.dodal.application.plan.usecase.GetPlansOfGoalByDateUseCase;
import ac.dnd.dodal.application.plan.usecase.GetWeeklyAchievementRateOfGoalUseCase;
import ac.dnd.dodal.application.plan.usecase.GetPlanUseCase;
import ac.dnd.dodal.application.plan.dto.query.GetPlansOfHistoryQuery;
import ac.dnd.dodal.application.plan.dto.query.GetPlansOfGoalQuery;
import ac.dnd.dodal.application.plan.dto.query.GetWeeklyAchievementRateOfGoalQuery;
import ac.dnd.dodal.application.plan.dto.query.GetPlanQuery;
import ac.dnd.dodal.application.plan.dto.PlanModel;
import ac.dnd.dodal.ui.plan.response.PlanElement;
import ac.dnd.dodal.ui.goal.response.DailyAchievementRateElement;

@Service
@RequiredArgsConstructor
public class PlanQueryService implements 
    GetPlansOfHistoryUseCase, 
    GetPlansOfGoalByDateUseCase,
    GetWeeklyAchievementRateOfGoalUseCase,
    GetPlanUseCase {

    private final PlanRepository planRepository;
    private final PlanService planService;

    private final int PLAN_HISTORY_CHUNK_SIZE = 3;

    @Override
    public List<PlanElement> getPlanHistory(GetPlanQuery query) {
        return planRepository.findHistoriesByPlanId(query.planId(), query.userId(), PLAN_HISTORY_CHUNK_SIZE);
    }

    @Override
    public Page<PlanElement> getPlansOfHistory(GetPlansOfHistoryQuery query) {
        if (!planService.isExistByUserIdAndGoalIdAndHistoryId(query.userId(), query.goalId(),
                query.historyId())) {
            throw new ForbiddenException();
        }
        return planRepository.findAllByHistoryId(query.historyId(), query.pageable());
    }

    @Override
    public List<PlanElement> getPlansOfGoalByDate(GetPlansOfGoalQuery query) {
        if (!planService.isExistByUserIdAndGoalId(query.userId(), query.goalId())) {
            throw new ForbiddenException();
        }

        List<PlanModel> plans = planRepository.findAllByGoalIdAndDate(query.goalId(),
                query.date().atStartOfDay().minusDays(query.range()),
                query.date().atStartOfDay().plusDays(query.range() + 1));
        return plans.stream()
            .map(PlanModel::toPlanElement)
            .collect(Collectors.toList());
    }

    // Todo: 로직 개선 필요. 너무 비효율적이야.
    @Override
    public List<DailyAchievementRateElement> getWeeklyAchievementRateOfGoal(
            GetWeeklyAchievementRateOfGoalQuery query) {
        if (!planService.isExistByUserIdAndGoalId(query.userId(), query.goalId())) {
            throw new ForbiddenException();
        }

        LocalDate startDate = query.date();
        LocalDate endDate = startDate.plusDays(7);

        List<PlanModel> plans = planRepository.findAllByGoalIdAndDate(query.goalId(),
                startDate.atStartOfDay(), endDate.atStartOfDay());
        List<DailyAchievementRateElement> dailyAchievementRateElements = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            LocalDate date = startDate.plusDays(i);
            int successCount = 0;
            int totalCount = 0;
            int failureCount = 0;
            for (PlanModel plan : plans) {
                if (isPlanForDate(plan, date)) {
                    totalCount++;
                    if (plan.getStatus() == PlanStatus.SUCCESS) {
                        successCount++;
                    }
                    if (plan.getStatus() == PlanStatus.FAILURE) {
                        failureCount++;
                    }
                }
            }
            dailyAchievementRateElements.add(new DailyAchievementRateElement(
                date, failureCount, successCount, totalCount));
        }

        return dailyAchievementRateElements;
    }

    private boolean isPlanForDate(PlanModel plan, LocalDate date) {
        LocalDateTime startDateTime = date.atStartOfDay();
        LocalDateTime endDateTime = date.atStartOfDay().plusDays(1);

        return (plan.getStartDate().isBefore(endDateTime) &&
                plan.getEndDate().isAfter(startDateTime)) ||
                (plan.getStartDate().isBefore(endDateTime) &&
                plan.getEndDate().isAfter(startDateTime)) ||
                plan.getStartDate().isEqual(startDateTime) ||
                plan.getEndDate().isEqual(startDateTime);
    }
}
