package ac.dnd.dodal.acceptance.feedback.steps;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import ac.dnd.dodal.domain.feedback.enums.FeedbackType;

public class GetFeedbackQuestionAndIndicatorsStep {

    private static final String BASE_URL = "api/feedbacks";

    public static Response getFeedbackQuestionAndIndicators(FeedbackType type) {
        return given().log().all()
                .param("type", type)
            .when()
                .get(BASE_URL)
            .then().log().all()
                .extract()
            .response();
    }
}
