package ac.dnd.dodal.acceptance.user_guide.steps;

import static io.restassured.RestAssured.given;

import java.util.Map;

import io.restassured.response.Response;

public class GetUserGuideStep {

    private final static String BASE_URL = "/api/user-guides";

    public static Response getUserGuide(Map<String, Object> header, String type) {
        return given().log().all()
                .headers(header)
                .queryParam("type", type)
            .when()
                .get(BASE_URL)
            .then().log().all()
                .extract()
            .response();
    }
    
}
