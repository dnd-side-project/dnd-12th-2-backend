package ac.dnd.dodal.ui.plan;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.domain.plan.model.Plan;
import ac.dnd.dodal.domain.plan.enums.PlanStatus;
import ac.dnd.dodal.application.plan.usecase.CompletePlanUseCase;
import ac.dnd.dodal.ui.feedback.request.CreateFeedbackRequest;

@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanController {

    private final CompletePlanUseCase completePlanUseCase;

    @PostMapping("/{planId}/complete")
    public ApiResponse<?> completePlan(
        @PathVariable Long planId,
        @RequestParam String status,
        @RequestBody CreateFeedbackRequest request
    ) {
        Page<Plan> plans = completePlanUseCase.completePlan(request.toCommand(planId, PlanStatus.of(status)));

        return ApiResponse.success(plans);
    }
}
