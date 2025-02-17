package ac.dnd.dodal.application.goal.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import ac.dnd.dodal.domain.goal.model.GoalStatistics;
import ac.dnd.dodal.common.exception.NotFoundException;
import ac.dnd.dodal.common.exception.UnauthorizedException;
import ac.dnd.dodal.domain.goal.exception.GoalExceptionCode;
import ac.dnd.dodal.application.goal.repository.GoalStatisticsRepository;
import ac.dnd.dodal.application.goal.usecase.GetGoalSuccessRateUseCase;
import ac.dnd.dodal.application.goal.dto.query.GetGoalSuccessRateQuery;
import ac.dnd.dodal.ui.goal.response.GoalStatisticsResponse;

@Service
@RequiredArgsConstructor
public class GoalStatisticsService implements GetGoalSuccessRateUseCase {

    private final GoalStatisticsRepository goalStatisticsRepository;

    public void save(GoalStatistics goalStatistics) {
        goalStatisticsRepository.save(goalStatistics);
    }

    public GoalStatistics findByIdOrThrow(Long id) {
        return goalStatisticsRepository.findById(id).orElseThrow(
                () -> new NotFoundException(GoalExceptionCode.GOAL_STATISTICS_NOT_FOUND));
    }

    @Override
    public GoalStatisticsResponse getGoalSuccessRate(GetGoalSuccessRateQuery query) {
        // goal이 삭제된 경우에는 statisctics 존재 x -> 이에 대한 예외처리 x
        GoalStatistics goalStatistics = findByIdOrThrow(query.goalId());
        if (goalStatistics.getGoal().getUserId() != query.userId()) {
            throw new UnauthorizedException();
        }

        return GoalStatisticsResponse.of(goalStatistics);
    }
}
