package ac.dnd.dodal.acceptance.plan;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import io.restassured.response.Response;
import io.restassured.common.mapper.TypeRef;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import ac.dnd.dodal.AcceptanceTest;
import ac.dnd.dodal.common.enums.CommonResultCode;
import ac.dnd.dodal.ui.plan.response.PlanElement;
import ac.dnd.dodal.acceptance.plan.steps.PlanSteps;
import ac.dnd.dodal.common.response.ApiResponse;

public class GetPlanAcceptanceTest extends AcceptanceTest {

    @Test
    public void get_plan_history_success() {
        Response response = PlanSteps.getPlan(6L, authorizationHeader);
        ApiResponse<List<PlanElement>> apiResponse = response.as(new TypeRef<ApiResponse<List<PlanElement>>>() {});

        log.info("apiResponse: {}", apiResponse);
        // 200
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        // COM001
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // Success
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
        // size is greater than 0
        assertThat(apiResponse.data().size()).isGreaterThan(0);
        // PlanElement
        assertThat(apiResponse.data().get(0)).isInstanceOf(PlanElement.class);
    }
}
