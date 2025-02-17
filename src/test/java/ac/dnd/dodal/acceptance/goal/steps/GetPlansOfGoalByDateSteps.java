package ac.dnd.dodal.acceptance.goal.steps;

import static io.restassured.RestAssured.given;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import io.restassured.response.Response;

public class GetPlansOfGoalByDateSteps {

    private static final String BASE_URL = "/api/goals/{goalId}/plans";

    public static Response getPlansOfGoalByDate(
            Map<String, Object> header, Long goalId, LocalDate date) {
        String url = BASE_URL.replace("{goalId}", goalId.toString());
        String dateString = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return given().log().all()
                .headers(header)
                .queryParam("date", dateString)
            .when()
                .get(url)
            .then().log().all()
                .extract()
            .response();
    }
}
