package ac.dnd.dodal.acceptance.goal.steps;

import java.util.Map;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;
import io.restassured.http.ContentType;

import ac.dnd.dodal.ui.goal.request.CreateGoalRequest;
import ac.dnd.dodal.ui.goal.request.CreateGoalAndPlanRequest;

public class GoalSteps {

    private static final String BASE_URL = "/api/goals";

    public static Response createGoal(Map<String, Object> header, CreateGoalRequest body) {

        return given().log().all()
                    .contentType(ContentType.JSON)
                    .headers(header)
                    .body(body)
                .when()
                    .post(BASE_URL)
                .then().log().all()
                    .extract()
                .response();
    }
    
    public static Response createGoalAndPlan(
            Map<String, Object> header, CreateGoalAndPlanRequest body) {
        String url = BASE_URL + "/with-plan";
            
        return given().log().all()
                .contentType(ContentType.JSON)
                .headers(header)
                .body(body)
            .when()
                .post(url)
            .then().log().all()
                .extract()
            .response();
    }

    public static Response achieveGoal(Map<String, Object> header, Long goalId) {
        return given().log().all()
                .headers(header)
            .when()
                .patch(BASE_URL + "/" + goalId + "/achieve")
            .then().log().all()
                .extract()
            .response();
    }

    public static Response getGoal(Map<String, Object> header, Long goalId) {
        return given().log().all()
                .headers(header)
            .when()
                .get(BASE_URL + "/" + goalId)
            .then().log().all()
                .extract()
            .response();
    }

    public static Response deleteGoal(Map<String, Object> header, Long goalId) {
        return given().log().all()
                .headers(header)
            .when()
                .delete(BASE_URL + "/" + goalId)
            .then().log().all()
                .extract()
            .response();
    }
}
