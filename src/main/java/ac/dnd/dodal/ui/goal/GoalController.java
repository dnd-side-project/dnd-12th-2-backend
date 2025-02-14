package ac.dnd.dodal.ui.goal;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ac.dnd.dodal.common.annotation.UserId;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.application.goal.usecase.*;
import ac.dnd.dodal.application.goal.dto.command.*;
import ac.dnd.dodal.ui.goal.request.*;

@RestController
@RequestMapping("/api/goals")
@RequiredArgsConstructor
public class GoalController {

    private final CreateGoalUseCase createGoalUseCase;
    private final AchieveGoalUseCase achieveGoalUseCase;
    private final DeleteGoalUseCase deleteGoalUseCase;

    @PostMapping
    public ApiResponse<Long> createGoal(@UserId Long userId, @RequestBody CreateGoalRequest request) {
        CreateGoalCommand command = new CreateGoalCommand(userId, request.title());
        Long goalId = createGoalUseCase.create(command);

        return ApiResponse.success(goalId);
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
