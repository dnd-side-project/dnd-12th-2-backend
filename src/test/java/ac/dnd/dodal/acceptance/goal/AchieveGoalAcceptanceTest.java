package ac.dnd.dodal.acceptance.goal;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.http.HttpStatus;

import ac.dnd.dodal.AcceptanceTest;
import ac.dnd.dodal.acceptance.goal.steps.GoalSteps;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.common.enums.CommonResultCode;
import ac.dnd.dodal.ui.goal.request.GoalCreateRequest;
import ac.dnd.dodal.ui.goal.fixture.GoalUIFixture;
import ac.dnd.dodal.domain.goal.exception.GoalExceptionCode;

public class AchieveGoalAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("Achieve Goal Test")
    public void achieve_goal() {
        // given
        GoalCreateRequest request = GoalUIFixture.createGoalRequest("test");

        // when
        Response response = GoalSteps.createGoal(authorizationHeader, request);
        ApiResponse<Long> apiResponse = response.as(new TypeRef<ApiResponse<Long>>() {});

        // then 200
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        // COM001
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // Success
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
        // data does not exist
        assertThat(response.getBody().jsonPath().getMap("data")).isNull();
    }

    @Test
    @DisplayName("Achieve Goal Test with Unauthorized User")
    public void achieve_goal_with_unauthorized_user() {
        // given
        GoalCreateRequest request = GoalUIFixture.createGoalRequest("test");

        // when
        Response response = GoalSteps.createGoal(authorizationHeader, request);
        ApiResponse<Long> apiResponse = response.as(new TypeRef<ApiResponse<Long>>() {});

        // then 401
        assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
        // COM003
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.UNAUTHORIZED.getCode());
        // Unauthorized
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.UNAUTHORIZED.getMessage());
        // data does not exist
        assertThat(response.getBody().jsonPath().getMap("data")).isNull();
    }

    @Test
    @DisplayName("Achieve Goal Test with Deleted Goal")
    public void achieve_goal_with_deleted_goal() {
        // given
        GoalCreateRequest request = GoalUIFixture.createGoalRequest("test");

        // when
        Response response = GoalSteps.createGoal(authorizationHeader, request);
        ApiResponse<Long> apiResponse = response.as(new TypeRef<ApiResponse<Long>>() {});

        // then 403
        assertThat(response.statusCode()).isEqualTo(HttpStatus.FORBIDDEN.value());
        // GOA003
        assertThat(apiResponse.code()).isEqualTo(GoalExceptionCode.DELETED_GOAL.getCode());
        // Wrong Access: Deleted goal
        assertThat(apiResponse.message()).isEqualTo(GoalExceptionCode.DELETED_GOAL.getMessage());
        // data does not exist
        assertThat(response.getBody().jsonPath().getMap("data")).isNull();
    }

    @Test
    @DisplayName("Achieve Goal Test with Already Achieved Goal")
    public void achieve_goal_with_already_achieved_goal() {
        // given
        GoalCreateRequest request = GoalUIFixture.createGoalRequest("test");

        // when
        Response response = GoalSteps.createGoal(authorizationHeader, request);
        ApiResponse<Long> apiResponse = response.as(new TypeRef<ApiResponse<Long>>() {});

        // then 403
        assertThat(response.statusCode()).isEqualTo(HttpStatus.FORBIDDEN.value());
        // GOA005
        assertThat(apiResponse.code()).isEqualTo(GoalExceptionCode.GOAL_ALREADY_ACHIEVED.getCode());
        // Goal already achieved
        assertThat(apiResponse.message()).isEqualTo(GoalExceptionCode.GOAL_ALREADY_ACHIEVED.getMessage());
        // data does not exist
        assertThat(response.getBody().jsonPath().getMap("data")).isNull();
    }
}
