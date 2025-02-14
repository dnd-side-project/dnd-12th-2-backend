package ac.dnd.dodal.acceptance.auth;

import static org.assertj.core.api.Assertions.assertThat;

import ac.dnd.dodal.AcceptanceTest;
import ac.dnd.dodal.acceptance.auth.steps.AuthSteps;
import ac.dnd.dodal.common.enums.CommonResultCode;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.core.security.enums.SecurityExceptionCode;
import ac.dnd.dodal.ui.auth.response.JwtTokenDto;
import ac.dnd.dodal.ui.auth.response.UserInfoResponseDto;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class refreshAccessTokenAcceptanceTest extends AcceptanceTest {

  @Test
  @DisplayName("Refresh Access Token Test")
  public void refresh_access_token() {
    // when
    Response response = AuthSteps.refreshAccessToken(refreshAuthorizationHeader);
    ApiResponse<UserInfoResponseDto> apiResponse = response.as(new TypeRef<ApiResponse<UserInfoResponseDto>>() {});
    UserInfoResponseDto userInfoResponseDto = apiResponse.data();
    JwtTokenDto jwtTokenDto = userInfoResponseDto.jwtTokenDto();
    // then 200
    assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    // COM001
    assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
    // Success
    assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
    // data
    assertThat(response.getBody().jsonPath().getMap("data").get("email")).isEqualTo(userEmail);
    assertThat(jwtTokenDto.accessToken()).isNotNull();
    assertThat(jwtTokenDto.refreshToken()).isNotNull();
  }

  @Test
  @DisplayName("Refresh Access Token Test with Unauthorized Refresh Token")
    public void refresh_access_token_with_unauthorized_refresh_token() {
        // when
        Response response = AuthSteps.refreshAccessToken(unauthorizedRefreshAuthorizationHeader);
        ApiResponse<UserInfoResponseDto> apiResponse = response.as(new TypeRef<ApiResponse<UserInfoResponseDto>>() {});
        // then 401
        assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
        // COM002
        assertThat(apiResponse.code()).isEqualTo(SecurityExceptionCode.ACCESS_DENIED_ERROR.getCode());
        // Unauthorized
        assertThat(apiResponse.message()).isEqualTo(SecurityExceptionCode.ACCESS_DENIED_ERROR.getMessage());
        // data does not exist
        assertThat(response.getBody().jsonPath().getMap("data")).isNull();
    }
}
