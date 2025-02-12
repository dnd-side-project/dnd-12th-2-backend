package ac.dnd.dodal.acceptance.goal;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.http.HttpStatus;

import ac.dnd.dodal.AcceptanceTest;
import ac.dnd.dodal.acceptance.goal.steps.AddPlanToGoalSteps;
import ac.dnd.dodal.common.enums.CommonResultCode;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.ui.plan.request.AddNewPlanRequest;
import ac.dnd.dodal.ui.plan.request.AddSamePlanRequest;
import ac.dnd.dodal.ui.plan.request.CreatePlanRequest;
import ac.dnd.dodal.ui.plan.fixture.PlanUIFixture;

public class AddPlanToGoalAcceptanceTest extends AcceptanceTest {

    Long goalId = 1L;
    Long planHistoryId = 1L;
    Long lastSuccessPlanIdWithHistory_1 = 17L;
    Long lastFailurePlanHistoryId = 2L;
    Long lastFailurePlanIdWithHistory_2 = 36L;

    @DisplayName("Create plan")
    @Test
    public void create_plan() {
        // given
        CreatePlanRequest request = PlanUIFixture.createPlanRequest();

        // when
        Response response =
                AddPlanToGoalSteps.createPlan(goalId, authorizationHeader, request);
        ApiResponse<Long> apiResponse = response.as(new TypeRef<ApiResponse<Long>>() {});

        // then 200
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());
        // COM001
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // Success
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
        // Created Plan History Id
        assertThat(apiResponse.data()).isNotNull();
    }

    @DisplayName("Add plan to existing history when success")
    @Test
    public void add_plan_to_existing_history_when_success() {
        // given
        AddSamePlanRequest request = PlanUIFixture.addSamePlanRequest();

        // when
        Response response = AddPlanToGoalSteps.addPlanWhenSuccess(goalId, planHistoryId,
                lastSuccessPlanIdWithHistory_1, authorizationHeader, request);
        ApiResponse<Long> apiResponse = response.as(new TypeRef<ApiResponse<Long>>() {});

        // then 200
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());
        // COM001
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // Success
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
        // Existing Plan History Id
        assertThat(apiResponse.data()).isNotNull();
    }

    @DisplayName("Add plan to existing history when failure")
    @Test
    public void add_plan_to_existing_history_when_failure() {
        // given
        AddNewPlanRequest request = PlanUIFixture.addNewPlanRequest();

        // when
        Response response = AddPlanToGoalSteps.addPlanWhenFailure(goalId, lastFailurePlanHistoryId,
                lastFailurePlanIdWithHistory_2, authorizationHeader, request);
        ApiResponse<Long> apiResponse = response.as(new TypeRef<ApiResponse<Long>>() {});

        // then 200
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());
        // COM001
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // Success
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
        // Existing Plan History Id
        assertThat(apiResponse.data()).isNotNull();
    }
}
