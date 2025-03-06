package ac.dnd.dodal.acceptance.goal;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.http.HttpStatus;

import ac.dnd.dodal.AcceptanceTest;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.common.enums.CommonResultCode;
import ac.dnd.dodal.ui.plan.response.PlanElement;
import ac.dnd.dodal.acceptance.goal.steps.GetPlansOfGoalByDateSteps;

public class GetPlansOfGoalByDateAcceptacneTest extends AcceptanceTest {    

    @Test
    @DisplayName("Get Plans of Goal by Date Test")
    public void get_plans_of_goal_by_date() {
        // when
        Response response =
                GetPlansOfGoalByDateSteps.getPlansOfGoalByDate(authorizationHeader, goalId, date, 1);
        ApiResponse<List<PlanElement>> apiResponse =
                response.as(new TypeRef<ApiResponse<List<PlanElement>>>() {});

        log.info("response: {}", response.asString());
        log.info("apiResponse: {}", apiResponse);
        // then 200
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        // COM001
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // Success
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
        // data does not exist
        assertThat(apiResponse.data().size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Get Weekly Plans of Goal by Date Test")
    public void get_weekly_plans_of_goal_by_date() {
        // when
        Response response = GetPlansOfGoalByDateSteps
                .getPlansOfGoalByDate(authorizationHeader, goalId, date, 7);
        ApiResponse<List<PlanElement>> apiResponse = 
            response.as(new TypeRef<ApiResponse<List<PlanElement>>>() {});

        log.info("response: {}", response.asString());
        log.info("apiResponse: {}", apiResponse);
        // then 200
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        // COM001
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // Success
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
        // data does not exist
        assertThat(apiResponse.data().size()).isGreaterThan(0);
    }
}
