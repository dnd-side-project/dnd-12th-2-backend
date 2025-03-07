package ac.dnd.dodal.acceptance.user;

import static org.assertj.core.api.Assertions.assertThat;

import ac.dnd.dodal.AcceptanceTest;
import ac.dnd.dodal.acceptance.user.steps.UserSteps;
import ac.dnd.dodal.common.enums.CommonResultCode;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.domain.onboarding.exception.OnBoardingExceptionCode;
import ac.dnd.dodal.ui.user.request.CreateUserAnswerRequestDto;
import ac.dnd.dodal.ui.user.response.GetUserAnswerResponseDto;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.http.HttpStatus;
import org.testcontainers.shaded.org.checkerframework.common.value.qual.DoesNotMatchRegex;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CreateUserAnswerAcceptanceTest extends AcceptanceTest {

    @Test
    @Order(1)
    @DisplayName("온보딩을 마치지 않은 사용자에 대한 결과 조회")
    void getUserAnswersByNoOnboardingUser() {
        // when
        Response response = UserSteps.getOnboardingQuestions(authorizationHeader);
        ApiResponse<GetUserAnswerResponseDto> apiResponse =
                response.as(new TypeRef<ApiResponse<GetUserAnswerResponseDto>>() {});
        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        // COM001
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // Success
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
        // data
        assertThat(apiResponse.data().checkOnboardingDone()).isEqualTo(false);
        assertThat(apiResponse.data().data()).isEmpty();
    }

    @Test
    @Order(2)
    @DisplayName("잘못된 Question Id로 인한 온보딩 사용자 답변 저장 실패")
    void failSaveUserAnswerByWrongQuestionId() {
        // given
        Long wrongQuestionId = 8L;
        Long correctAnswerId = 1L;
        CreateUserAnswerRequestDto request = new CreateUserAnswerRequestDto(List.of(
                new CreateUserAnswerRequestDto.UserAnswerData(1L, 2L),
                new CreateUserAnswerRequestDto.UserAnswerData(2L, 3L),
                new CreateUserAnswerRequestDto.UserAnswerData(wrongQuestionId, correctAnswerId)  // 8번 질문은 없음
        ));

        // when
        Response response = UserSteps.addUserAnswer(authorizationHeader, request);
        ApiResponse<?> apiResponse =
                response.as(new TypeRef<ApiResponse<?>>() {});
        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.NOT_FOUND.value());
        // ANS001
        assertThat(apiResponse.code()).isEqualTo(OnBoardingExceptionCode.NOT_FOUND_ANSWER.getCode());
        // Failure
        String expectedMessage = String.format("Answer (%s) for question (%s) does not exist.", correctAnswerId, wrongQuestionId);
        assertThat(apiResponse.message()).isEqualTo(expectedMessage);
    }

    @Test
    @Order(3)
    @DisplayName("잘못된 Answer Id로 인한 온보딩 사용자 답변 저장 실패")
    void failSaveUserAnswerByWrongAnswerId() {
        // given
        Long correctQuestionId = 3L;
        Long wrongAnswerId = 11L;
        CreateUserAnswerRequestDto request = new CreateUserAnswerRequestDto(List.of(
                new CreateUserAnswerRequestDto.UserAnswerData(1L, 2L),
                new CreateUserAnswerRequestDto.UserAnswerData(2L, 3L),
                new CreateUserAnswerRequestDto.UserAnswerData(correctQuestionId, wrongAnswerId)  // 11번 Answer는 없음
        ));

        // when
        Response response = UserSteps.addUserAnswer(authorizationHeader, request);
        ApiResponse<?> apiResponse =
                response.as(new TypeRef<ApiResponse<?>>() {});
        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.NOT_FOUND.value());
        // ANS001
        assertThat(apiResponse.code()).isEqualTo(OnBoardingExceptionCode.NOT_FOUND_ANSWER.getCode());
        // Failure
        String expectedMessage = String.format("Answer (%s) for question (%s) does not exist.", wrongAnswerId, correctQuestionId);
        assertThat(apiResponse.message()).isEqualTo(expectedMessage);
    }

    @Test
    @Order(4)
    @DisplayName("온보딩 사용자 답변 저장 실패 - Question의 수와 사용자의 답변 개수 불일치")
    void failSaveUserAnswerByInvalidAnswerCount() {
        // given
        CreateUserAnswerRequestDto request = new CreateUserAnswerRequestDto(List.of(
                // 질문은 총 3개지만 요청은 2개만 들어온 상황 (불일치)
                new CreateUserAnswerRequestDto.UserAnswerData(1L, 2L),
                new CreateUserAnswerRequestDto.UserAnswerData(2L, 3L)
        ));

        // when
        Response response = UserSteps.addUserAnswer(authorizationHeader, request);
        ApiResponse<?> apiResponse =
                response.as(new TypeRef<ApiResponse<?>>() {});
        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        // ANS002
        assertThat(apiResponse.code()).isEqualTo(OnBoardingExceptionCode.INVALID_ANSWER_COUNT.getCode());
        // Failure
        assertThat(apiResponse.message()).isEqualTo(OnBoardingExceptionCode.INVALID_ANSWER_COUNT.getMessage());
    }


    @Test
    @Order(5)
    @DisplayName("온보딩 사용자 답변 저장")
    void addUserAnswer() {
        // given
        CreateUserAnswerRequestDto request = new CreateUserAnswerRequestDto(List.of(
                new CreateUserAnswerRequestDto.UserAnswerData(1L, 2L),
                new CreateUserAnswerRequestDto.UserAnswerData(2L, 3L),
                new CreateUserAnswerRequestDto.UserAnswerData(3L, 1L)
        ));

        // when
        Response response = UserSteps.addUserAnswer(authorizationHeader, request);
        ApiResponse<?> apiResponse =
                response.as(new TypeRef<ApiResponse<?>>() {});
        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        // COM001
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // Success
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
    }

    @Test
    @Order(6)
    @DisplayName("온보딩을 마친 사용자에 대한 결과 및 답변 조회")
    void getUserAnswerByAlreadyDoneUser() {
        // given
        CreateUserAnswerRequestDto request = new CreateUserAnswerRequestDto(List.of(
                new CreateUserAnswerRequestDto.UserAnswerData(1L, 2L),
                new CreateUserAnswerRequestDto.UserAnswerData(2L, 3L),
                new CreateUserAnswerRequestDto.UserAnswerData(3L, 1L)
        ));

        // when
        UserSteps.addUserAnswer(authorizationHeader, request);

        Response response = UserSteps.getOnboardingQuestions(authorizationHeader);
        ApiResponse<GetUserAnswerResponseDto> apiResponse =
                response.as(new TypeRef<ApiResponse<GetUserAnswerResponseDto>>() {});
        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        // COM000
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // SUCCESS
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
        // data
        assertThat(apiResponse.data().checkOnboardingDone()).isEqualTo(true);
        assertThat(apiResponse.data().data().size()).isEqualTo(3);

    }

}
