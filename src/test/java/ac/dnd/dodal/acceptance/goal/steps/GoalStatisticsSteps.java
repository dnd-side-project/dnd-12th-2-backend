package ac.dnd.dodal.acceptance.goal.steps;

import static io.restassured.RestAssured.given;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import io.restassured.response.Response;

public class GoalStatisticsSteps {
    
    private static final String BASE_URL = "/api/goals/{goalId}/statistics";

    public static Response getGoalSuccessRate(
        Map<String, Object> header, Long goalId) {
        String url = BASE_URL.replace("{goalId}", goalId.toString());

        return given().log().all()
                .headers(header)
            .when()
                .get(url)
            .then().log().all()
                .extract()
            .response();
    }

    public static Response getWeeklyAchievementRateOfGoal(
        Map<String, Object> header, Long goalId, LocalDate date) {
        String url = BASE_URL.replace("{goalId}", goalId.toString()) + "/weekly";
        String dateString = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return given().log().all()
                .headers(header)
                .param("date", dateString)
            .when()
                .get(url)
            .then().log().all()
                .extract()
            .response();
    }
}
