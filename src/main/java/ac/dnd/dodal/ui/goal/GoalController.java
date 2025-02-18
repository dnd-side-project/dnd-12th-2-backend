package ac.dnd.dodal.ui.goal;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import ac.dnd.dodal.common.annotation.UserId;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.application.goal.usecase.*;
import ac.dnd.dodal.application.goal.dto.command.*;
import ac.dnd.dodal.ui.goal.request.*;
import ac.dnd.dodal.ui.goal.response.GoalStatisticsResponse;

@RestController
@RequestMapping("/api/goals")
@RequiredArgsConstructor
public class GoalController {

    private final CreateGoalUseCase createGoalUseCase;
    private final AchieveGoalUseCase achieveGoalUseCase;
    private final DeleteGoalUseCase deleteGoalUseCase;
    private final GetGoalUseCase getGoalUseCase;

    @PostMapping
    public ApiResponse<Long> createGoal(@UserId Long userId,
            @RequestBody CreateGoalRequest request) {
        CreateGoalCommand command = new CreateGoalCommand(userId, request.title());
        Long goalId = createGoalUseCase.create(command);

        return ApiResponse.success(goalId);
    }

    @GetMapping
    public ApiResponse<List<GoalStatisticsResponse>> getUnAchievedGoals(@UserId Long userId) {
        List<GoalStatisticsResponse> goalStatisticsResponses 
            = getGoalUseCase.getUnAchievedGoals(userId);

        return ApiResponse.success(goalStatisticsResponses);
    }

    @GetMapping("achieve")
    public ApiResponse<List<GoalStatisticsResponse>> getAchievedGoals(@UserId Long userId) {
        List<GoalStatisticsResponse> goalStatisticsResponses 
            = getGoalUseCase.getAchievedGoals(userId);

        return ApiResponse.success(goalStatisticsResponses);
    }
    

    @PatchMapping("/{goalId}/achieve")
    public ApiResponse<Void> achieveGoal(@UserId Long userId, @PathVariable Long goalId) {
        AchieveGoalCommand command = new AchieveGoalCommand(userId, goalId);
        achieveGoalUseCase.achieve(command);

        return ApiResponse.success();
    }

    @DeleteMapping("/{goalId}")
    public ApiResponse<Void> deleteGoal(@UserId Long userId, @PathVariable Long goalId) {
        DeleteGoalCommand command = new DeleteGoalCommand(userId, goalId);
        deleteGoalUseCase.delete(command);

        return ApiResponse.success();
    }
}
