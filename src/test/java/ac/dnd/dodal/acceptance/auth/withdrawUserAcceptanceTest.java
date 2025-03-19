package ac.dnd.dodal.acceptance.auth;

import static org.assertj.core.api.Assertions.assertThat;

import ac.dnd.dodal.AcceptanceTest;
import ac.dnd.dodal.acceptance.auth.steps.AuthSteps;
import ac.dnd.dodal.common.enums.CommonResultCode;
import ac.dnd.dodal.common.response.ApiResponse;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.http.HttpStatus;

public class withdrawUserAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("Withdraw User Test")
    public void withdrawUser() {
        // when
        Response response = AuthSteps.deleteUser(authorizationHeader3);
        ApiResponse<?> apiResponse =
                response.as(new TypeRef<ApiResponse<?>>() {});
        // then 200
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        // COM001
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // Success
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
    }
}
