package ac.dnd.dodal.acceptance.goal;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.response.Response;
import io.restassured.common.mapper.TypeRef;

import org.springframework.http.HttpStatus;

import ac.dnd.dodal.AcceptanceTest;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.common.response.ApiResponse.PageResponse;
import ac.dnd.dodal.common.enums.CommonResultCode;
import ac.dnd.dodal.acceptance.goal.steps.GetPlanHistoriesSteps;
import ac.dnd.dodal.ui.plan_history.response.HistoryResponse;

public class GetPlanHistoriesAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("get plan histories")
    public void get_plan_histories() {
        Response response = GetPlanHistoriesSteps
            .getPlanHistories(authorizationHeader, goalId, 0, 4);
        ApiResponse<PageResponse<HistoryResponse>> apiResponse =
                response.as(new TypeRef<ApiResponse<PageResponse<HistoryResponse>>>() {});

        log.info("apiResponse: {}", apiResponse);
        // then 200
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        // Success
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // then size is 4
        assertThat(apiResponse.data().content().size()).isEqualTo(4);
    }
}
