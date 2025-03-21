package ac.dnd.dodal.ui.plan;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import ac.dnd.dodal.common.annotation.UserId;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.domain.plan.model.Plan;
import ac.dnd.dodal.domain.plan.enums.PlanStatus;
import ac.dnd.dodal.application.plan.usecase.CompletePlanUseCase;
import ac.dnd.dodal.application.plan.usecase.DeletePlanUseCase;
import ac.dnd.dodal.application.plan.usecase.GetPlanUseCase;
import ac.dnd.dodal.application.plan.dto.command.DeletePlanCommand;
import ac.dnd.dodal.application.plan.dto.query.GetPlanQuery;
import ac.dnd.dodal.ui.feedback.request.CreateFeedbackRequest;
import ac.dnd.dodal.ui.plan.response.PlanElement;

import java.util.List;

@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanController {

    private final CompletePlanUseCase completePlanUseCase;
    private final DeletePlanUseCase deletePlanUseCase;
    private final GetPlanUseCase getPlanUseCase;

    @PostMapping("/{planId}/complete")
    public ApiResponse<PlanElement> completePlan(
        @UserId Long userId,
        @PathVariable Long planId,
        @RequestParam String status,
        @RequestBody CreateFeedbackRequest request
    ) {
        Plan plan = completePlanUseCase
                .completePlan(request.toCommand(userId, planId, PlanStatus.of(status)));

        return ApiResponse.success(plans);
    }

    @GetMapping("/{planId}/history")
    public ApiResponse<List<PlanElement>> getPlanHistory(
        @UserId Long userId,
        @PathVariable Long planId
    ) {
        return ApiResponse.success(getPlanUseCase.getPlanHistory(new GetPlanQuery(userId, planId)));
    }

    @DeleteMapping("/{planId}")
    public ApiResponse<Void> deletePlan(
        @UserId Long userId,
        @PathVariable Long planId
    ) {
        deletePlanUseCase.delete(new DeletePlanCommand(userId, planId));

        return ApiResponse.success();
    }
}
