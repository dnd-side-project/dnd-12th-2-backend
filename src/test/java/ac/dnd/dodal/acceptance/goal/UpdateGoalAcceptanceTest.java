package ac.dnd.dodal.acceptance.goal;

import static org.assertj.core.api.Assertions.assertThat;

import ac.dnd.dodal.AcceptanceTest;
import ac.dnd.dodal.acceptance.goal.steps.GoalSteps;
import ac.dnd.dodal.common.enums.CommonResultCode;
import ac.dnd.dodal.common.response.ApiResponse;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class UpdateGoalAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("Update Goal Title Test")
    public void update_goal() {
        //given
        Long goalId = 5L;
        String title = "modified title";
        
        // when
        Response response = GoalSteps.updateGoal(authorizationHeader, goalId, title);
        ApiResponse<?> apiResponse = response.as(new TypeRef<ApiResponse<?>>() {});

        // then 200
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        // COM000
        assertThat(apiResponse.code()).isEqualTo(CommonResultCode.SUCCESS.getCode());
        // Success
        assertThat(apiResponse.message()).isEqualTo(CommonResultCode.SUCCESS.getMessage());
        // data does not exist
        assertThat(response.getBody().jsonPath().getMap("data")).isNull();
    }
}
