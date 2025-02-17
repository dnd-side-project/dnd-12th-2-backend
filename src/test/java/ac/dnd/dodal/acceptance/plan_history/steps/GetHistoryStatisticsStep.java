package ac.dnd.dodal.acceptance.plan_history.steps;

import static io.restassured.RestAssured.given;

import java.util.Map;

import io.restassured.response.Response;

public class GetHistoryStatisticsStep {

    private static final String BASE_URL 
        = "/api/goals/{goalId}/plan-histories/{historyId}/statistics";

    public static Response getHistoryStatistics(
        Map<String, Object> header, Long userId, Long goalId, Long historyId) {
        String url = BASE_URL.replace("{goalId}", goalId.toString())
                .replace("{historyId}", historyId.toString());

        return given().log().all()
                .headers(header)
            .when()
                .get(url)
            .then().log().all()
                .extract()
            .response();
    }
}
