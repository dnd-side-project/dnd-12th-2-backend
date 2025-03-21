package ac.dnd.dodal.acceptance.plan.steps;

import static io.restassured.RestAssured.given;

import java.util.Map;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import ac.dnd.dodal.ui.feedback.request.CreateFeedbackRequest;

public class PlanSteps {
    
    private static final String BASE_URL = "/api/plans";
    private static final String COMPLETE_PLAN_URL = BASE_URL + "/{planId}/complete?status={status}";
    private static final String DELETE_PLAN_URL = BASE_URL + "/{planId}";
    private static final String GET_PLAN_HISTORY_URL = BASE_URL + "/{planId}/history";

    public static Response completePlan(Long planId, String status,
            Map<String, Object> header, CreateFeedbackRequest request) {
        String url = COMPLETE_PLAN_URL
            .replace("{planId}", planId.toString())
            .replace("{status}", status);

        return given().log().all()
                .contentType(ContentType.JSON)
                .headers(header)
                .body(request)
            .when()
                .post(url)
            .then().log().all()
                .extract()
                .response();
    }

    public static Response getPlan(Long planId, Map<String, Object> header) {
        String url = GET_PLAN_HISTORY_URL
            .replace("{planId}", planId.toString());

        return given().log().all()
                .contentType(ContentType.JSON)
                .headers(header)
            .when()
                .get(url)
            .then().log().all()
                .extract()
                .response();
    }

    public static Response deletePlan(Long planId, Map<String, Object> header) {
        String url = DELETE_PLAN_URL
            .replace("{planId}", planId.toString());

        return given().log().all()
                .contentType(ContentType.JSON)
                .headers(header)
            .when()
                .delete(url)
            .then().log().all()
                .extract()
            .response();
    }
}
