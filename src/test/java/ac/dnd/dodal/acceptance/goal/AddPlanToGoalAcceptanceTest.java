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
import ac.dnd.dodal.ui.plan.request.CreateFirstPlanRequest;
import ac.dnd.dodal.ui.plan.request.CreatePlanRequest;
import ac.dnd.dodal.ui.plan.fixture.PlanUIFixture;

public class AddPlanToGoalAcceptanceTest extends AcceptanceTest {

    Long goalId = 1L;
    Long planHistoryId = 1L;

    @DisplayName("Add first plan with new history to goal")
    @Test
    public void add_first_plan_with_new_history_to_goal() {
        // given
        CreateFirstPlanRequest request = PlanUIFixture.createFirstPlanRequest();

        // when
        Response response =
                AddPlanToGoalSteps.addFirstPlanToHistory(goalId, authorizationHeader, request);
        ApiResponse<Long> apiResponse = response.as(new TypeRef<ApiResponse<Long>>() {});

        // then 200
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());
        // COM001
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // Success
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
        // Created Plan Id
        assertThat(apiResponse.data()).isNotNull();
    }

    @DisplayName("Add plan to existing history")
    @Test
    public void add_plan_to_existing_history() {
        // given
        CreatePlanRequest request = PlanUIFixture.createPlanRequest();

        // when
        Response response = AddPlanToGoalSteps.addPlanToExistingHistory(goalId, planHistoryId, authorizationHeader, request);
        ApiResponse<Long> apiResponse = response.as(new TypeRef<ApiResponse<Long>>() {});

        // then 200
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());
        // COM001
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // Success
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
        // Created Plan Id
        assertThat(apiResponse.data()).isNotNull();
    }
}
