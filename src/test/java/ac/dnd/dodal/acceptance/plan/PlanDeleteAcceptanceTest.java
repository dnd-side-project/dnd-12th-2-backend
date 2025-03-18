package ac.dnd.dodal.acceptance.plan;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import ac.dnd.dodal.AcceptanceTest;
import ac.dnd.dodal.acceptance.plan.steps.PlanSteps;
import ac.dnd.dodal.common.enums.CommonResultCode;
import ac.dnd.dodal.common.response.ApiResponse;

public class PlanDeleteAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("Plan Delete Test")
    void plan_delete_test() {
        // given
        Map<String, Object> header = authorizationHeader;

        // when
        Response response = PlanSteps.deletePlan(planId, header);
        ApiResponse<Void> apiResponse = response.as(new TypeRef<ApiResponse<Void>>() {});

        log.info("response = {}", response.asString());

        // then 200
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        // then COM000
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // then Success
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
        // then data null
        assertThat(apiResponse.data()).isNull();
    }
}