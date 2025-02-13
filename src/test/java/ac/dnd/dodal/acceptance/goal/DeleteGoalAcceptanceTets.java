package ac.dnd.dodal.acceptance.goal;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ac.dnd.dodal.AcceptanceTest;
import ac.dnd.dodal.acceptance.goal.steps.GoalSteps;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.common.enums.CommonResultCode;
import ac.dnd.dodal.domain.goal.exception.GoalExceptionCode;

import org.springframework.http.HttpStatus;

public class DeleteGoalAcceptanceTets extends AcceptanceTest {

    private Long goalId = 1L;
    private Long deletedGoalId = 2L;

    @Test
    @DisplayName("Delete Goal Test")
    public void delete_goal() {
        // when
        Response response = GoalSteps.deleteGoal(authorizationHeader, goalId);
        ApiResponse<?> apiResponse = response.as(new TypeRef<ApiResponse<?>>() {});

        // then 200
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        // COM000
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // Success
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
        // data does not exist
        assertThat(response.getBody().jsonPath().getMap("data")).isNull();
    }

    @Test
    @DisplayName("Delete Goal Test with Already Deleted Goal")
    public void delete_goal_with_already_deleted_goal() {
        // when
        Response response = GoalSteps.deleteGoal(authorizationHeader, deletedGoalId);
        ApiResponse<?> apiResponse = response.as(new TypeRef<ApiResponse<?>>() {});

        // then 403
        assertThat(response.statusCode()).isEqualTo(HttpStatus.FORBIDDEN.value());
        // GOA006
        assertThat(apiResponse.code()).isEqualTo(GoalExceptionCode.GOAL_ALREADY_DELETED.getCode());
        // Goal already deleted
        assertThat(apiResponse.message())
                .isEqualTo(GoalExceptionCode.GOAL_ALREADY_DELETED.getMessage());
        // data does not exist
        assertThat(response.getBody().jsonPath().getMap("data")).isNull();
    }

    // Todo: 인증이 추가된 이후 테스트 추가
//     @Test
//     @DisplayName("Delete Goal Test with Unauthorized User")
//     public void delete_goal_with_unauthorized_user() {
//         // when
//         Response response = GoalSteps.deleteGoal(authorizationHeader, goalId);
//         ApiResponse<?> apiResponse = response.as(new TypeRef<ApiResponse<?>>() {});

//         // then 401
//         assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
//         // COM003
//         assertThat(apiResponse.code()).isEqualTo(CommonResultCode.UNAUTHORIZED.getCode());
//         // Unauthorized
//         assertThat(apiResponse.message()).isEqualTo(CommonResultCode.UNAUTHORIZED.getMessage());
//         // data does not exist
//         assertThat(response.getBody().jsonPath().getMap("data")).isNull();
//     }
}
