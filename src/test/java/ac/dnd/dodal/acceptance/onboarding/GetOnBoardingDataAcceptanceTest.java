package ac.dnd.dodal.acceptance.onboarding;

import ac.dnd.dodal.AcceptanceTest;
import ac.dnd.dodal.acceptance.onboarding.steps.OnBoardingSteps;
import ac.dnd.dodal.common.enums.CommonResultCode;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.core.security.enums.SecurityExceptionCode;
import ac.dnd.dodal.ui.onboarding.response.GetOnBoardingResponseDto;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GetOnBoardingDataAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("온보딩 데이터를 조회한다.")
    public void getOnboardingData() {
        // when
        Response response = OnBoardingSteps.getQuestionsAndAnswers(authorizationHeader);
        ApiResponse<List<GetOnBoardingResponseDto>> apiResponse = response.as(new TypeRef<ApiResponse<List<GetOnBoardingResponseDto>>>() {});

        // then 200
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        // COM001
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // Success
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
        // data exist
        assertThat(apiResponse.data().get(0)).isNotNull();
    }

    @Test
    @DisplayName("온보딩 데이터를 조회 실패한다.")
    public void getFailOnboardingData() {
        // when
        Response response = OnBoardingSteps.getQuestionsAndAnswers(unauthorizedAuthorizationHeader);
        ApiResponse<List<GetOnBoardingResponseDto>> apiResponse =
                response.as(new TypeRef<ApiResponse<List<GetOnBoardingResponseDto>>>() {});

        // then 403
        assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
        // SEC001
        assertThat(apiResponse.code()).isEqualTo(SecurityExceptionCode.TOKEN_MALFORMED_ERROR.getCode());
        // Token Malformed Error
        assertThat(apiResponse.message())
                .isEqualTo(SecurityExceptionCode.TOKEN_MALFORMED_ERROR.getMessage());
        // data exist
        assertThat(apiResponse.data()).isNull();
    }
}