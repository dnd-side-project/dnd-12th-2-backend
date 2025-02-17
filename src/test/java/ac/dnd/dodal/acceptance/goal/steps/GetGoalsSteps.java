package ac.dnd.dodal.acceptance.goal.steps;

import static io.restassured.RestAssured.given;

import java.util.Map;

import io.restassured.response.Response;

public class GetGoalsSteps {
    
    private static final String BASE_URL = "/api/goals";

    public static Response getUnAchievedGoals(Map<String, Object> header) {
        return given().log().all()
                .headers(header)
            .when()
                .get(BASE_URL)
            .then().log().all()
                .extract()
            .response();
    }

    public static Response getAchievedGoals(Map<String, Object> header) {
        return given().log().all()
                .headers(header)
            .when()
                .get(BASE_URL + "/achieve")
            .then().log().all()
                .extract()
            .response();
    }
}
