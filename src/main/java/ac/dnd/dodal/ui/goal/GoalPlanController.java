package ac.dnd.dodal.ui.goal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import ac.dnd.dodal.common.annotation.UserId;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.ui.plan.request.CreateFirstPlanRequest;
import ac.dnd.dodal.ui.plan.request.CreatePlanRequest;

@RestController
@RequestMapping("/api/goals/{goalId}")
public class GoalPlanController {

    @PostMapping("/plans")
    public ApiResponse<?> addFirstPlanToHistory(
            @UserId Long userId,
            @PathVariable Long goalId,
            @RequestBody CreateFirstPlanRequest request) {
        //TODO: process POST request

        return ApiResponse.success();
    }

    @PostMapping("/plan-histories/{planHistoryId}/plans")
    public ApiResponse<?> addPlanToExistingHistory(
            @UserId Long userId,
            @PathVariable Long goalId,
            @PathVariable Long planHistoryId,
            @RequestBody CreatePlanRequest request) {
        //TODO: process POST request

        return ApiResponse.success();
    }
}
