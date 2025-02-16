package ac.dnd.dodal.ui.goal;

import java.time.LocalDate;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ac.dnd.dodal.common.annotation.UserId;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.application.plan.usecase.AddPlanUseCase;
import ac.dnd.dodal.application.plan.usecase.CreatePlanAndHistoryUseCase;
import ac.dnd.dodal.application.plan.usecase.GetPlansOfGoalByDateUseCase;
import ac.dnd.dodal.application.plan.dto.query.GetPlansOfGoalQuery;
import ac.dnd.dodal.ui.plan.request.AddNewPlanRequest;
import ac.dnd.dodal.ui.plan.request.AddSamePlanRequest;
import ac.dnd.dodal.ui.plan.request.CreatePlanRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/goals/{goalId}")
public class GoalPlanController {

    private final AddPlanUseCase addPlanUseCase;
    private final CreatePlanAndHistoryUseCase createPlanAndHistoryUseCase;
    private final GetPlansOfGoalByDateUseCase getPlansOfGoalByDateUseCase;

    @GetMapping("/plans")
    public ApiResponse<?> getPlansOfGoalByDate(
        @UserId Long userId,
        @PathVariable Long goalId,
        @RequestParam LocalDate date) {
        GetPlansOfGoalQuery query = new GetPlansOfGoalQuery(userId, goalId, date);

        return ApiResponse.success(getPlansOfGoalByDateUseCase.getPlansOfGoalByDate(query));
    }

    @PostMapping("/plans")
    public ApiResponse<?> createPlan(
        @UserId Long userId,
        @PathVariable Long goalId,
        @RequestBody CreatePlanRequest request) {
        createPlanAndHistoryUseCase
                .createPlanAndHistory(request.toCreatePlanAndHistoryCommand(userId, goalId));

        return ApiResponse.success();
    }

    @PostMapping("/plan-histories/{planHistoryId}/plans/{planId}/success")
    public ApiResponse<?> addPlanWhenSuccess(
            @UserId Long userId,
            @PathVariable Long goalId,
            @PathVariable Long planHistoryId,
            @PathVariable Long planId,
            @RequestBody AddSamePlanRequest request) {
        addPlanUseCase
                .addSamePlan(request.toAddSamePlanCommand(userId, goalId, planHistoryId, planId));

        return ApiResponse.success();
    }

    @PostMapping("/plan-histories/{planHistoryId}/plans/{planId}/failure")
    public ApiResponse<?> addPlanWhenFailure(
        @UserId Long userId,
        @PathVariable Long goalId,
        @PathVariable Long planHistoryId,
        @PathVariable Long planId,
        @RequestBody AddNewPlanRequest request) {
        addPlanUseCase.addNewPlan(request.toAddNewPlanCommand(userId, goalId, planHistoryId, planId));

        return ApiResponse.success();
    }
}
