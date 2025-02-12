package ac.dnd.dodal.acceptance.goal.steps;

import static io.restassured.RestAssured.given;

import java.util.Map;

import io.restassured.response.Response;
import io.restassured.http.ContentType;

import ac.dnd.dodal.ui.plan.request.CreateFirstPlanRequest;
import ac.dnd.dodal.ui.plan.request.CreatePlanRequest;

public class AddPlanToGoalSteps {

    private static final String BASE_URL = "/api/goals/{goalId}/plans";
    private static final String BASE_URL_WITH_HISTORY = "/api/goals/{goalId}/plan-histories/{planHistoryId}/plans";

    public static Response addFirstPlanToHistory(Long goalId, Map<String, Object> header,
            CreateFirstPlanRequest body) {
        String url = BASE_URL.replace("{goalId}", goalId.toString());

        return given().log().all()
                .contentType(ContentType.JSON)
                .headers(header)
                .body(body)
            .when()
                .post(url)
            .then().log().all()
                .extract()
            .response();
    }
    
    public static Response addPlanToExistingHistory(Long goalId, Long planHistoryId, Map<String, Object> header, CreatePlanRequest body) {
        String url = BASE_URL_WITH_HISTORY
                .replace("{goalId}", goalId.toString())
                .replace("{planHistoryId}", planHistoryId.toString());

        return given().log().all()
                .contentType(ContentType.JSON)
                .headers(header)
                .body(body)
            .when()
                .post(url)
            .then().log().all()
                .extract()
            .response();
    }
}
