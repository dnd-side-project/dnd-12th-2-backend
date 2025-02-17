package ac.dnd.dodal.acceptance.goal.steps;

import static io.restassured.RestAssured.given;
import java.util.Map;
import io.restassured.response.Response;

public class GetPlanHistoriesSteps {

    public static final String BASE_URL = "/api/goals/{goalId}/plan-histories";

    public static Response getPlanHistories(
        Map<String, Object> header, Long goalId, int page, int size) {
        String url = BASE_URL.replace("{goalId}", goalId.toString());

        return given().log().all()
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
