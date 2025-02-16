package ac.dnd.dodal.application.plan.service;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import ac.dnd.dodal.application.plan.repository.PlanRepository;
import ac.dnd.dodal.application.plan.usecase.GetPlansOfHistoryUseCase;
import ac.dnd.dodal.application.plan.usecase.GetPlansOfGoalByDateUseCase;
import ac.dnd.dodal.application.plan.usecase.GetWeeklyAchievementRateOfGoalUseCase;
import ac.dnd.dodal.common.exception.ForbiddenException;
import ac.dnd.dodal.application.plan.dto.query.GetPlansOfHistoryQuery;
import ac.dnd.dodal.application.plan.dto.query.GetPlansOfGoalQuery;
import ac.dnd.dodal.application.plan.dto.query.GetWeeklyAchievementRateOfGoalQuery;
import ac.dnd.dodal.ui.plan.response.PlanElement;
import ac.dnd.dodal.ui.goal.response.DailyAchievementRateElement;
// import ac.dnd.dodal.ui.plan.response.PlanWithHistoryElement;

@Service
@RequiredArgsConstructor
public class PlanQueryService implements 
    GetPlansOfHistoryUseCase, 
    GetPlansOfGoalByDateUseCase,
    GetWeeklyAchievementRateOfGoalUseCase {

    private final PlanRepository planRepository;

    @Override
    public Page<PlanElement> getPlansOfHistory(GetPlansOfHistoryQuery query) {
        if (!isExistByUserIdAndGoalIdAndHistoryId(query.userId(), query.goalId(),
                query.historyId())) {
            throw new ForbiddenException();
        }
        return planRepository.findAllByHistoryId(query.historyId(), query.pageable());
    }

    @Override
    public List<PlanElement> getPlansOfGoalByDate(GetPlansOfGoalQuery query) {
        if (!isExistByUserIdAndGoalId(query.userId(), query.goalId())) {
            throw new ForbiddenException();
        }

        return planRepository.findAllByGoalIdAndDate(
            query.goalId(), query.date().atStartOfDay(), query.date().atStartOfDay().plusDays(1));
    }
    // @Override
    // public List<PlanWithHistoryElement> getPlansOfGoalByDate(GetPlansOfGoalQuery query) {
    //     if (!isExistByUserIdAndGoalId(query.userId(), query.goalId())) {
    //         throw new ForbiddenException();
    //     }

    //     return planRepository.findAllWithPreviousHistoryByGoalIdAndDate(
    //         query.goalId(), query.date().atStartOfDay());
    // }
    private boolean isPlanForDate(PlanElement plan, LocalDate date) {
        LocalDateTime startDateTime = date.atStartOfDay();
        LocalDateTime endDateTime = date.atStartOfDay().plusDays(1);

        return (plan.startDate().isBefore(endDateTime) &&
                plan.endDate().isAfter(startDateTime)) ||
                (plan.startDate().isBefore(endDateTime) &&
                plan.endDate().isEqual(startDateTime));
    }

    private boolean isExistByUserIdAndGoalIdAndHistoryId(
        Long userId, Long goalId, Long historyId) {
        return planRepository.isExistByUserIdAndGoalIdAndHistoryId(userId, goalId, historyId);
    }

    private boolean isExistByUserIdAndGoalId(Long userId, Long goalId) {
        return planRepository.isExistByUserIdAndGoalId(userId, goalId);
    }
}
