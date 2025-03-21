package ac.dnd.dodal.application.plan.service;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import ac.dnd.dodal.application.plan.repository.PlanRepository;
import ac.dnd.dodal.domain.plan.model.Plan;
import ac.dnd.dodal.common.exception.NotFoundException;
import ac.dnd.dodal.domain.plan.exception.PlanExceptionCode;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepository;

    public Plan save(Plan plan) {
        return planRepository.save(plan);
    }

    public Plan saveAndFlush(Plan plan) {
        return planRepository.saveAndFlush(plan);
    }

    public List<Plan> saveAll(List<Plan> plans) {
        return planRepository.saveAll(plans);
    }

    public Optional<Plan> findById(Long planId) {
        return planRepository.findById(planId);
    }

    public Plan findByIdOrThrow(Long planId) {
        return planRepository.findById(planId)
                .orElseThrow(() -> new NotFoundException(PlanExceptionCode.PLAN_NOT_FOUND));
    }

    public Optional<Plan> findLatestPlanByHistoryId(Long historyId) {
        return planRepository.findLatestPlanByHistoryId(historyId);
    }

    public Plan findLatestPlanByHistoryIdOrThrow(Long historyId) {
        return planRepository.findLatestPlanByHistoryId(historyId)
                .orElseThrow(() -> new NotFoundException(PlanExceptionCode.PLAN_NOT_FOUND));
    }

    public boolean isExistByUserIdAndGoalIdAndHistoryId(
        Long userId, Long goalId, Long historyId) {
        return planRepository.isExistByUserIdAndGoalIdAndHistoryId(userId, goalId, historyId);
    }

    public boolean isExistByUserIdAndGoalId(Long userId, Long goalId) {
        return planRepository.isExistByUserIdAndGoalId(userId, goalId);
    }
}
