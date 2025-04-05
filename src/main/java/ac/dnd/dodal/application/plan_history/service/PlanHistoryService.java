package ac.dnd.dodal.application.plan_history.service;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import ac.dnd.dodal.common.exception.NotFoundException;
import ac.dnd.dodal.domain.plan_history.exception.PlanHistoryExceptionCode;
import ac.dnd.dodal.domain.plan_history.model.PlanHistory;
import ac.dnd.dodal.application.plan_history.repository.PlanHistoryRepository;

@Service
@RequiredArgsConstructor
public class PlanHistoryService {

    private final PlanHistoryRepository planHistoryRepository;

    public PlanHistory save(PlanHistory planHistory) {
        return planHistoryRepository.save(planHistory);
    }

    public PlanHistory saveAndFlush(PlanHistory planHistory) {
        return planHistoryRepository.saveAndFlush(planHistory);
    }

    public List<PlanHistory> saveAll(List<PlanHistory> planHistories) {
        return planHistoryRepository.saveAll(planHistories);
    }

    public Optional<PlanHistory> findById(Long planHistoryId) {
        return planHistoryRepository.findById(planHistoryId);
    }

    public PlanHistory findByIdOrThrow(Long planHistoryId) {
        return planHistoryRepository.findById(planHistoryId).orElseThrow(
                () -> new NotFoundException(PlanHistoryExceptionCode.PLAN_HISTORY_NOT_FOUND));
    }

    public void delete(PlanHistory planHistory) {
        planHistory.delete();
        planHistoryRepository.save(planHistory);
    }

    public List<PlanHistory> findAllByGoalId(Long goalId) {
        return planHistoryRepository.findAllByGoalId(goalId);
    }
}
