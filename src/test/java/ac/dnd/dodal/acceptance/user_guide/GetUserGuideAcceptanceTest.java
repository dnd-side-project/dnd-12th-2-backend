package ac.dnd.dodal.acceptance.user_guide;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.response.Response;
import io.restassured.common.mapper.TypeRef;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import ac.dnd.dodal.AcceptanceTest;
import ac.dnd.dodal.acceptance.user_guide.steps.GetUserGuideStep;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.common.enums.CommonResultCode;
import ac.dnd.dodal.ui.user_guide.response.UserGuideResponse;
import ac.dnd.dodal.ui.user_guide.response.NewGoalAndPlanGuidesResponse;

public class GetUserGuideAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("Get New Plan User Guide Test")
    void get_new_plan_user_guide() {
        Response response = GetUserGuideStep.getUserGuide(authorizationHeader, "new-plan");
        ApiResponse<UserGuideResponse> apiResponse =
                response.as(new TypeRef<ApiResponse<UserGuideResponse>>() {});

        log.info("response: {}", response.asString());
        log.info("apiResponse: {}", apiResponse);
        // then 200 응답
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        // COM000
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // Success
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
        // Data exists
        assertThat(apiResponse.data()).isNotNull();
    }
    
    @Test
    @DisplayName("Get New Goal User Guide Test")
    void get_new_goal_user_guide() {
        Response response = GetUserGuideStep.getUserGuide(authorizationHeader, "new-goal");
        ApiResponse<UserGuideResponse> apiResponse =
                response.as(new TypeRef<ApiResponse<UserGuideResponse>>() {});

        log.info("response: {}", response.asString());
        log.info("apiResponse: {}", apiResponse);
        // then 200 응답
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        // COM000
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // Success
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
        // Data exists
        assertThat(apiResponse.data()).isNotNull();
    }
    
    @Test
    @DisplayName("Get Update Plan User Guide Test")
    void get_update_plan_user_guide() {
        Response response = GetUserGuideStep.getUserGuide(authorizationHeader, "update-plan");
        ApiResponse<UserGuideResponse> apiResponse =
                response.as(new TypeRef<ApiResponse<UserGuideResponse>>() {});

        log.info("response: {}", response.asString());
        log.info("apiResponse: {}", apiResponse);
        // then 200 응답
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        // COM000
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // Success
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
        // Data exists
        assertThat(apiResponse.data()).isNotNull();
    }

    @Test
    @DisplayName("Get New Goal and Plan User Guides Test")
    void get_new_goal_and_plan_user_guides() {
        Response response = GetUserGuideStep.getNewGoalAndPlanGuides(authorizationHeader);
        ApiResponse<NewGoalAndPlanGuidesResponse> apiResponse =
                response.as(new TypeRef<ApiResponse<NewGoalAndPlanGuidesResponse>>() {});

        log.info("response: {}", response.asString());
        log.info("apiResponse: {}", apiResponse);
        // then 200 응답
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        // COM000
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // Success
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
        // Data exists
        assertThat(apiResponse.data()).isNotNull();
    }
}