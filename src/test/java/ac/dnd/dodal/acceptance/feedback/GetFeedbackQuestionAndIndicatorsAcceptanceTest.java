package ac.dnd.dodal.acceptance.feedback;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import ac.dnd.dodal.AcceptanceTest;
import ac.dnd.dodal.acceptance.feedback.steps.GetFeedbackQuestionAndIndicatorsStep;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.common.enums.CommonResultCode;
import ac.dnd.dodal.domain.feedback.enums.FeedbackType;
import ac.dnd.dodal.ui.feedback.response.FeedbackQuestionAndIndicatorsResponse;

public class GetFeedbackQuestionAndIndicatorsAcceptanceTest extends AcceptanceTest {
    
    @DisplayName("get success feedback question and indicators when success")
    @Test
    void get_success_feedback_question_and_indicators_when_success() {
        Response response = GetFeedbackQuestionAndIndicatorsStep
                .getFeedbackQuestionAndIndicators(FeedbackType.SUCCESS);
        ApiResponse<FeedbackQuestionAndIndicatorsResponse> apiResponse =
                response.as(new TypeRef<ApiResponse<FeedbackQuestionAndIndicatorsResponse>>() {});

        log.info("apiResponse: {}", apiResponse);
        // then 200 
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        // COM000
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // Success
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
        // data is not null
        assertThat(apiResponse.data()).isNotNull();
        // data size is greater than 0
        assertThat(apiResponse.data().elements().size()).isGreaterThan(0);
    }

    @DisplayName("get failure feedback question and indicators when success")
    @Test
    void get_failure_feedback_question_and_indicators_when_success() {
        Response response = GetFeedbackQuestionAndIndicatorsStep
                .getFeedbackQuestionAndIndicators(FeedbackType.FAILURE);
        ApiResponse<FeedbackQuestionAndIndicatorsResponse> apiResponse =
                response.as(new TypeRef<ApiResponse<FeedbackQuestionAndIndicatorsResponse>>() {});

        log.info("apiResponse: {}", apiResponse);
        // then 200 
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        // COM000
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // Success
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
        // data is not null
        assertThat(apiResponse.data()).isNotNull();
        // data size is greater than 0
        assertThat(apiResponse.data().elements().size()).isGreaterThan(0);
    }
}
