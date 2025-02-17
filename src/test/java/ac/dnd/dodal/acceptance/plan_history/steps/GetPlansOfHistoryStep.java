package ac.dnd.dodal.acceptance.plan_history.steps;

import static io.restassured.RestAssured.given;

import java.util.Map;

import io.restassured.response.Response;
import io.restassured.http.ContentType;


public class GetPlansOfHistoryStep {

    private static final String BASE_URL = "/api/goals/{goalId}/plan-histories/{historyId}";

    public static Response getPlansOfHistory(
            Map<String, Object> header, Long goalId, Long historyId, int page, int size) {
        String url = BASE_URL.replace("{goalId}", goalId.toString())
                .replace("{historyId}", historyId.toString());

        return given().log().all()
                .contentType(ContentType.JSON)
                .headers(header)
                .queryParam("page", page)
                .queryParam("size", size)
            .when()
                .get(url)
            .then().log().all()
                .extract()
            .response();
    }
}
