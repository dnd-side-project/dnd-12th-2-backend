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
import ac.dnd.dodal.application.plan.repository.PlanRepository;
import ac.dnd.dodal.application.plan.usecase.GetPlansOfHistoryUseCase;
import ac.dnd.dodal.application.plan.usecase.GetPlansOfGoalByDateUseCase;
import ac.dnd.dodal.application.plan.usecase.GetWeeklyAchievementRateOfGoalUseCase;
import ac.dnd.dodal.application.plan.dto.query.GetPlansOfHistoryQuery;
import ac.dnd.dodal.application.plan.dto.query.GetPlansOfGoalQuery;
import ac.dnd.dodal.application.plan.dto.query.GetWeeklyAchievementRateOfGoalQuery;
import ac.dnd.dodal.application.plan.dto.PlanModel;
import ac.dnd.dodal.ui.plan.response.PlanElement;
import ac.dnd.dodal.ui.goal.response.DailyAchievementRateElement;
import ac.dnd.dodal.ui.plan.response.PlanWithHistoryElement;

@Service
@RequiredArgsConstructor
public class PlanQueryService implements 
    GetPlansOfHistoryUseCase, 
    GetPlansOfGoalByDateUseCase,
    GetWeeklyAchievementRateOfGoalUseCase {

    private final PlanRepository planRepository;
    private final PlanService planService;

    @Override
    public Page<PlanElement> getPlansOfHistory(GetPlansOfHistoryQuery query) {
        if (!planService.isExistByUserIdAndGoalIdAndHistoryId(query.userId(), query.goalId(),
                query.historyId())) {
            throw new ForbiddenException();
        }
        return planRepository.findAllByHistoryId(query.historyId(), query.pageable());
    }

    // 3일치의 completed 된 history 함께 제공
    @Override
    public List<PlanWithHistoryElement> getPlansOfGoalByDate(GetPlansOfGoalQuery query) {
        if (!planService.isExistByUserIdAndGoalId(query.userId(), query.goalId())) {
            throw new ForbiddenException();
        }

        List<PlanModel> plans = planRepository.findAllByGoalIdAndDate(query.goalId(),
                query.date().atStartOfDay().minusDays(3), query.date().atStartOfDay().plusDays(1));
        List<Long> historyIds =
                plans.stream().map(PlanModel::getHistoryId).distinct().collect(Collectors.toList());
        List<PlanWithHistoryElement> planWithHistoryElements = new ArrayList<>();
        for (Long historyId : historyIds) {
            List<PlanElement> plansByHistoryId =
                    plans.stream().filter(plan -> plan.getHistoryId().equals(historyId)
                        && plan.getCompletedDate() != null)
                            .map(PlanModel::toPlanElement)
                            .collect(Collectors.toList());
            planWithHistoryElements.add(new PlanWithHistoryElement(historyId, plansByHistoryId));
        }
        return planWithHistoryElements;
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
            for (PlanModel plan : plans) {
                if (isPlanForDate(plan, date)) {
                    totalCount++;
                    if (plan.getCompletedDate() != null) {
                        successCount++;
                    }
                }
            }
            dailyAchievementRateElements.add(new DailyAchievementRateElement(date, successCount, totalCount));
        }

        return dailyAchievementRateElements;
    }

    private boolean isPlanForDate(PlanModel plan, LocalDate date) {
        LocalDateTime startDateTime = date.atStartOfDay();
        LocalDateTime endDateTime = date.atStartOfDay().plusDays(1);

        return (plan.getStartDate().isBefore(endDateTime) &&
                plan.getEndDate().isAfter(startDateTime)) ||
                (plan.getStartDate().isBefore(endDateTime) &&
                plan.getEndDate().isEqual(startDateTime));
    }
}
