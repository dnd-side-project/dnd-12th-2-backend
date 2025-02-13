package ac.dnd.dodal.ui.plan;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import ac.dnd.dodal.common.annotation.UserId;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.domain.plan.model.Plan;
import ac.dnd.dodal.domain.plan.enums.PlanStatus;
import ac.dnd.dodal.application.plan.usecase.CompletePlanUseCase;
import ac.dnd.dodal.ui.feedback.request.CreateFeedbackRequest;
import ac.dnd.dodal.ui.plan.response.PlanElement;

@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanController {

    private final CompletePlanUseCase completePlanUseCase;

    @PostMapping("/{planId}/complete")
    public ApiResponse<?> completePlan(
        @UserId Long userId,
        @PathVariable Long planId,
        @RequestParam String status,
        @RequestBody CreateFeedbackRequest request
    ) {
        Plan plan = completePlanUseCase.completePlan(request.toCommand(userId, planId, PlanStatus.of(status)));

        return ApiResponse.success(PlanElement.of(plan));
    }
}
