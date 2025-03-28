package ac.dnd.dodal.acceptance.goal;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import org.springframework.http.HttpStatus;

import ac.dnd.dodal.common.enums.CommonResultCode;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.AcceptanceTest;
import ac.dnd.dodal.acceptance.goal.steps.GoalSteps;
import ac.dnd.dodal.domain.goal.exception.GoalExceptionCode;
import ac.dnd.dodal.ui.goal.request.CreateGoalRequest;
import ac.dnd.dodal.ui.goal.request.CreateGoalAndPlanRequest;
import ac.dnd.dodal.ui.goal.fixture.GoalUIFixture;

public class CreateGoalAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("Create Goal and Plan Test with Valid Title")
    public void create_goal_and_plan() {
        // given
        CreateGoalAndPlanRequest request = GoalUIFixture.createGoalAndPlanRequest();

        // when
        Response response = GoalSteps.createGoalAndPlan(authorizationHeader, request);
        ApiResponse<Long> apiResponse = response.as(new TypeRef<ApiResponse<Long>>() {});

        log.info("apiResponse: {}", apiResponse);
        // then 200
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());
        // COM001
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // Success
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
        // Created Goal Id
        assertThat(apiResponse.data()).isNotNull();
    }

    @Test
    @DisplayName("Create Goal Test with Valid Title")
    public void create_goal() {
        // given
        CreateGoalRequest request = GoalUIFixture.createGoalRequest("test");

        // when
        Response response = GoalSteps.createGoal(authorizationHeader, request);
        ApiResponse<Long> apiResponse = response.as(new TypeRef<ApiResponse<Long>>() {});

        // then 200
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());
        // COM001
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // Success
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
        // Created Goal Id
        assertThat(apiResponse.data()).isNotNull();
    }

    @Test
    @DisplayName("Create Goal Test with Black Title (\"       \")")
    public void create_goal_with_black_title() {
        // given
        CreateGoalRequest request = GoalUIFixture.createBlackGoalRequest();

        // when
        Response response = GoalSteps.createGoal(authorizationHeader, request);
        ApiResponse<?> apiResponse = response.as(new TypeRef<ApiResponse<?>>() {});

        // then 400
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        // GOA002
        assertThat(apiResponse.code()).isEqualTo(GoalExceptionCode.GOAL_TITLE_EMPTY.getCode());
        // Goal title is empty
        assertThat(apiResponse.message())
                .isEqualTo(GoalExceptionCode.GOAL_TITLE_EMPTY.getMessage());
        // data does not exist
        assertThat(response.getBody().jsonPath().getMap("data")).isNull();
    }

    @Test
    @DisplayName("Create Goal Test with Empty Title (\"\")")
    public void create_goal_with_empty_title() {
        // given
        CreateGoalRequest request = GoalUIFixture.createEmptyGoalRequest();

        // when
        Response response = GoalSteps.createGoal(authorizationHeader, request);
        ApiResponse<?> apiResponse = response.as(new TypeRef<ApiResponse<?>>() {});

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        // GOA002
        assertThat(apiResponse.code()).isEqualTo(GoalExceptionCode.GOAL_TITLE_EMPTY.getCode());
        // Goal title is empty
        assertThat(apiResponse.message())
                .isEqualTo(GoalExceptionCode.GOAL_TITLE_EMPTY.getMessage());
        // data does not exist
        assertThat(response.getBody().jsonPath().getMap("data")).isNull();
    }

    @Test
    @DisplayName("Create Goal Test with Null Title (null)")
    public void create_goal_with_null_title() {
        // given
        CreateGoalRequest request = GoalUIFixture.createNullGoalRequest();

        // when
        Response response = GoalSteps.createGoal(authorizationHeader, request);
        ApiResponse<?> apiResponse = response.as(new TypeRef<ApiResponse<?>>() {});

        // then 400
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        // GOA002
        assertThat(apiResponse.code()).isEqualTo(GoalExceptionCode.GOAL_TITLE_EMPTY.getCode());
        // Goal title is empty
        assertThat(apiResponse.message())
            .isEqualTo(GoalExceptionCode.GOAL_TITLE_EMPTY.getMessage());
        // data does not exist
        assertThat(response.getBody().jsonPath().getMap("data")).isNull();
    }

    @Test
    @DisplayName("Create Goal Test with Exceed Title Length (21 characters)")
    public void create_goal_with_exceed_title_length() {
        // given
        CreateGoalRequest request = GoalUIFixture.createExceedTitleGoalRequest();

        // when
        Response response = GoalSteps.createGoal(authorizationHeader, request);
        ApiResponse<?> apiResponse = response.as(new TypeRef<ApiResponse<?>>() {});

        // then 400
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        // GOA001
        assertThat(apiResponse.code()).isEqualTo(GoalExceptionCode.GOAL_TITLE_EXCEED_MAX_LENGTH.getCode());
        // Goal title is too long: Max length is 20
        assertThat(apiResponse.message())
            .isEqualTo(GoalExceptionCode.GOAL_TITLE_EXCEED_MAX_LENGTH.getMessage());
        // data does not exist
        assertThat(response.getBody().jsonPath().getMap("data")).isNull();
    }
}
