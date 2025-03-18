package ac.dnd.dodal.acceptance.plan;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import ac.dnd.dodal.AcceptanceTest;
import ac.dnd.dodal.acceptance.plan.steps.PlanSteps;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.common.enums.CommonResultCode;
import ac.dnd.dodal.ui.feedback.request.CreateFeedbackRequest;
import ac.dnd.dodal.ui.feedback.fixture.FeedbackUIFixture;
import ac.dnd.dodal.domain.plan.exception.PlanExceptionCode;
import ac.dnd.dodal.ui.plan.response.PlanElement;

@Slf4j
public class PlanCompleteAcceptanceTest extends AcceptanceTest {

    private static final String SUCCESS_STATUS = "success";
    private static final String FAILURE_STATUS = "failure";
    private Long uncompletedPlanId2 = 32L;

    @Test
    @DisplayName("Complete Plan with Success Status Test")
    void complete_plan_with_success_status() {
        // given
        Map<String, Object> header = authorizationHeader;
        CreateFeedbackRequest feedbackRequest = FeedbackUIFixture.createFeedbackRequest();

        // when
        Response response =
                PlanSteps.completePlan(uncompletedPlanId, SUCCESS_STATUS, header, feedbackRequest);
        ApiResponse<PlanElement> apiResponse =
                response.as(new TypeRef<ApiResponse<PlanElement>>() {});

        log.info("response = {}", response.asString());
        // then 200
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        // COM001
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // Success
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
        // PlanElement
        assertThat(apiResponse.data()).isInstanceOf(PlanElement.class);
    }

    @Test
    @DisplayName("Complete Plan with Failure Status Test")
    void complete_plan_with_failure_status() {
        // given
        Map<String, Object> header = authorizationHeader;
        CreateFeedbackRequest feedbackRequest = FeedbackUIFixture.createFeedbackRequest();

        // when
        Response response =
                PlanSteps.completePlan(uncompletedPlanId2, FAILURE_STATUS, header, feedbackRequest);
        ApiResponse<PlanElement> apiResponse =
                response.as(new TypeRef<ApiResponse<PlanElement>>() {});

        log.info("response = {}", response.asString());
        // then 200
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        // COM001
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // Success
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
        // PlanElement
        assertThat(apiResponse.data()).isInstanceOf(PlanElement.class);
    }

    @Test
    @DisplayName("Complete Plan with Wrong Status Test")
    void complete_plan_with_wrong_status() {
        // given
        Map<String, Object> header = authorizationHeader;
        CreateFeedbackRequest feedbackRequest = FeedbackUIFixture.createFeedbackRequest();

        // when
        Response response =
                PlanSteps.completePlan(uncompletedPlanId, "wrong", header, feedbackRequest);
        ApiResponse<PlanElement> apiResponse =
                response.as(new TypeRef<ApiResponse<PlanElement>>() {});

        log.info("response = {}", response.asString());
        // then 403
        assertThat(response.statusCode()).isEqualTo(HttpStatus.FORBIDDEN.value());
        // PLN_009
        assertThat(apiResponse.code()).isEqualTo(PlanExceptionCode.INVALID_PLAN_STATUS.getCode());
        // Invalid Plan Status
        assertThat(apiResponse.message()).isEqualTo(PlanExceptionCode.INVALID_PLAN_STATUS.getMessage());
    }
}
