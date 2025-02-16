package ac.dnd.dodal.acceptance.goal;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import io.restassured.response.Response;
import io.restassured.common.mapper.TypeRef;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.http.HttpStatus;

import ac.dnd.dodal.AcceptanceTest;
import ac.dnd.dodal.common.enums.CommonResultCode;
import ac.dnd.dodal.acceptance.goal.steps.GoalStatisticsSteps;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.ui.goal.response.DailyAchievementRateElement;

public class GetGaolStatisticsAcceptanceTest extends AcceptanceTest {

    @DisplayName("Get weekly achievement rate of goal")
    @Test
    void get_weekly_achievement_rate_of_goal() {
        // when
        Response response = GoalStatisticsSteps.getWeeklyAchievementRateOfGoal(
                authorizationHeader, goalId, date);
        ApiResponse<List<DailyAchievementRateElement>> apiResponse
            = response.as(new TypeRef<ApiResponse<List<DailyAchievementRateElement>>>() {});

        log.info("apiResponse: {}", apiResponse);
        // then 200
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        // COM001
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // Success
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
        // need weekly, 7 days
        assertThat(apiResponse.data().size()).isEqualTo(7);
    }
}
