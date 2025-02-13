package ac.dnd.dodal.acceptance.goal.steps;

import static io.restassured.RestAssured.given;

import java.util.Map;

import io.restassured.response.Response;
import io.restassured.http.ContentType;

import ac.dnd.dodal.ui.plan.request.AddNewPlanRequest;
import ac.dnd.dodal.ui.plan.request.AddSamePlanRequest;
import ac.dnd.dodal.ui.plan.request.CreatePlanRequest;

public class AddPlanToGoalSteps {

    private static final String BASE_URL = "/api/goals/{goalId}/plans";
    private static final String BASE_URL_WITH_HISTORY = "/api/goals/{goalId}/plan-histories/{planHistoryId}/plans/{planId}";

    public static Response createPlan(Long goalId,
            Map<String, Object> header, CreatePlanRequest body) {
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

    public static Response addPlanWhenSuccess(Long goalId, Long planHistoryId, Long planId,
            Map<String, Object> header, AddSamePlanRequest body) {
        String url = BASE_URL_WITH_HISTORY
                .replace("{goalId}", goalId.toString())
                .replace("{planHistoryId}", planHistoryId.toString())
                .replace("{planId}", planId.toString())
                + "/success";

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

    public static Response addPlanWhenFailure(Long goalId, Long planHistoryId, Long planId,
            Map<String, Object> header, AddNewPlanRequest body) {
        String url = BASE_URL_WITH_HISTORY
                .replace("{goalId}", goalId.toString())
                .replace("{planHistoryId}", planHistoryId.toString())
                .replace("{planId}", planId.toString())
                + "/failure";

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
