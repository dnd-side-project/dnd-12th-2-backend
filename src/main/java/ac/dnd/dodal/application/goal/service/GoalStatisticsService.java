package ac.dnd.dodal.application.goal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ac.dnd.dodal.domain.goal.model.GoalStatistics;
import ac.dnd.dodal.common.exception.NotFoundException;
import ac.dnd.dodal.domain.goal.exception.GoalExceptionCode;
import ac.dnd.dodal.application.goal.repository.GoalStatisticsRepository;

@Service
@RequiredArgsConstructor
public class GoalStatisticsService {

    private final GoalStatisticsRepository goalStatisticsRepository;

    public void save(GoalStatistics goalStatistics) {
        goalStatisticsRepository.save(goalStatistics);
    }

    public GoalStatistics findByIdOrThrow(Long id) {
        return goalStatisticsRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(GoalExceptionCode.GOAL_STATISTICS_NOT_FOUND));
    }
}
