package ac.dnd.dodal.acceptance.plan_history;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;

import io.restassured.response.Response;
import io.restassured.common.mapper.TypeRef;

import org.springframework.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ac.dnd.dodal.AcceptanceTest;
import ac.dnd.dodal.acceptance.plan_history.steps.GetPlansOfHistoryStep;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.common.response.ApiResponse.PageResponse;
import ac.dnd.dodal.common.enums.CommonResultCode;
import ac.dnd.dodal.ui.plan.response.PlanElement;

@Slf4j
public class PlanHistoryAcceptenceTest extends AcceptanceTest {

    @Test
    @DisplayName("get plans of history with pagination")
    public void get_plans_of_history() {
        Response response = GetPlansOfHistoryStep
            .getPlansOfHistory(authorizationHeader, goalId, planHistoryId);
        ApiResponse<PageResponse<PlanElement>> apiResponse =
                response.as(new TypeRef<ApiResponse<PageResponse<PlanElement>>>() {});

        log.info("apiResponse: {}", apiResponse);
        // then 200
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        // COM001
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // Success
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
        // size
        assertThat(apiResponse.data().content().size()).isGreaterThan(0);
    }
}
