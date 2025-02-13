package ac.dnd.dodal.acceptance.plan.steps;

import static io.restassured.RestAssured.given;

import java.util.Map;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import ac.dnd.dodal.AcceptanceTest;
import ac.dnd.dodal.ui.feedback.request.CreateFeedbackRequest;

public class PlanSteps extends AcceptanceTest {
    
    private static final String COMPLETE_PLAN_URL = "/api/plans/{planId}/complete?status={status}";

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
}
