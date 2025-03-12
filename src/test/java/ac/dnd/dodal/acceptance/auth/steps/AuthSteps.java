package ac.dnd.dodal.acceptance.auth.steps;

import ac.dnd.dodal.AcceptanceTest;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthSteps extends AcceptanceTest {

    private static final String BASE_URL = "/api/auth";

    public static Response refreshAccessToken(Map<String, Object> header) {
        return given().log().all()
                .headers(header)
            .when()
                .post(BASE_URL + "/refresh")
            .then().log().all()
                .extract()
            .response();
    }

    public static Response deleteUser(Map<String, Object> header){
        return given().log().all()
                .headers(header)
            .when()
                .delete(BASE_URL + "/withdraw")
            .then().log().all()
                .extract()
            .response();
    }

}
