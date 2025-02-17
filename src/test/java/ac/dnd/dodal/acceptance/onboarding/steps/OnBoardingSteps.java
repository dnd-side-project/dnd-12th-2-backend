package ac.dnd.dodal.acceptance.onboarding.steps;

import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class OnBoardingSteps {

    private static final String BASE_URL = "/api/onboarding";

    public static Response getQuestionsAndAnswers(Map<String, Object> header) {
        return given().log().all()
                .headers(header)
                .when()
                .get(BASE_URL)
                .then().log().all()
                .extract()
                .response();
    }

    public static Response addUserAnswers(Map<String, Object> header, Map<String, Object> body) {
        return given().log().all()
                .contentType("application/json")
                .headers(header)
                .body(body)
                .when()
                .post(BASE_URL)
                .then().log().all()
                .extract()
                .response();
    }

}