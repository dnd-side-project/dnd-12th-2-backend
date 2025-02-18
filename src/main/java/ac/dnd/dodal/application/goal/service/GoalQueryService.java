package ac.dnd.dodal.application.goal.service;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import ac.dnd.dodal.application.goal.repository.GoalRepository;
import ac.dnd.dodal.application.goal.usecase.GetGoalUseCase;
import ac.dnd.dodal.ui.goal.response.GoalStatisticsResponse;

@Service
@RequiredArgsConstructor
public class GoalQueryService implements GetGoalUseCase {

    private final GoalRepository goalRepository;

    @Override
    public List<GoalStatisticsResponse> getUnAchievedGoals(Long userId) {
        return goalRepository.findUnAchievedGoalStatisticsResponsesByUserId(userId);
    }

    @Override
    public List<GoalStatisticsResponse> getAchievedGoals(Long userId) {
        return goalRepository.findAchievedGoalStatisticsResponsesByUserId(userId);
    }
}
