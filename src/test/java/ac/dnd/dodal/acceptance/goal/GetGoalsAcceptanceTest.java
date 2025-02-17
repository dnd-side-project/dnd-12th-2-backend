package ac.dnd.dodal.acceptance.goal;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.response.Response;
import io.restassured.common.mapper.TypeRef;

import org.springframework.http.HttpStatus;

import ac.dnd.dodal.AcceptanceTest;
import ac.dnd.dodal.common.enums.CommonResultCode;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.acceptance.goal.steps.GetGoalsSteps;
import ac.dnd.dodal.ui.goal.response.GoalStatisticsResponse;

public class GetGoalsAcceptanceTest extends AcceptanceTest {

    @DisplayName("Get unachieved goals")
    @Test
    void get_unachieved_goals_test() {
        Response response = GetGoalsSteps.getUnAchievedGoals(authorizationHeader);
        ApiResponse<List<GoalStatisticsResponse>> apiResponse
            = response.as(new TypeRef<ApiResponse<List<GoalStatisticsResponse>>>() {});

        log.info("apiResponse: {}", apiResponse);
        // then 200
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        // COM001
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // Success
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
        // data
        assertThat(apiResponse.data()).isNotEmpty();
    }

    @DisplayName("Get achieved goals")
    @Test
    void get_achieved_goals_test() {
        Response response = GetGoalsSteps.getAchievedGoals(authorizationHeader);
        ApiResponse<List<GoalStatisticsResponse>> apiResponse
            = response.as(new TypeRef<ApiResponse<List<GoalStatisticsResponse>>>() {});

        log.info("apiResponse: {}", apiResponse);
        // then 200
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        // COM001
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // Success
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
        // data
        assertThat(apiResponse.data()).isNotEmpty();
    }
}
