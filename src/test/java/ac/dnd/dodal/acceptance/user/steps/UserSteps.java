package ac.dnd.dodal.acceptance.user.steps;

import ac.dnd.dodal.ui.feedback.request.CreateFeedbackRequest;
import ac.dnd.dodal.ui.user.request.CreateUserAnswerRequestDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserSteps{

    private static final String BASE_URL = "/api/user";

    public static Response addUserAnswer(Map<String, Object> header, CreateUserAnswerRequestDto request) {
        String url = BASE_URL + "/onboarding";

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

    public static Response getOnboardingQuestions(Map<String, Object> header) {
        String url = BASE_URL + "/onboarding";

        return given().log().all()
                    .contentType(ContentType.JSON)
                    .headers(header)
                .when()
                    .get(url)
                .then().log().all()
                    .extract()
                    .response();
    }
}
