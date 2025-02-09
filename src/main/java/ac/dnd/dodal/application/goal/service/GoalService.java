package ac.dnd.dodal.application.goal.service;

import lombok.RequiredArgsConstructor;
import java.util.Optional;
import org.springframework.stereotype.Service;

import ac.dnd.dodal.application.goal.repository.GoalRepository;
import ac.dnd.dodal.common.exception.NotFoundException;
import ac.dnd.dodal.domain.goal.exception.GoalExceptionCode;
import ac.dnd.dodal.domain.goal.model.Goal;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;

    public Goal save(Goal goal) {
        return goalRepository.save(goal);
    }

    public Optional<Goal> findById(Long goalId) {
        return goalRepository.findById(goalId);
    }

    public Goal findByIdOrThrow(Long goalId) {
        return goalRepository.findById(goalId)
                .orElseThrow(() -> new NotFoundException(GoalExceptionCode.GOAL_NOT_FOUND));
    }
}
