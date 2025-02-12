package ac.dnd.dodal.acceptance.hello;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.response.Response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.http.MediaType;

import ac.dnd.dodal.AcceptanceTest;

public class HelloAcceptanceTest extends AcceptanceTest {

    @DisplayName("Hello API Test")
    @Test
    public void hello() {
        Response response =
                given().log().all()
                .when()
                    .get("/hello")
                .then().log().all()
                    .extract().response();

        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.getBody().jsonPath().getString("data"))
                .isEqualTo("Hello, Dodal World!");
    }

    @DisplayName("Hello API Test with JWT")
    @Test
    public void hello_with_jwt() {
        Response response =
                given().log().all()
                    .headers(authorizationHeader)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                    .get("/hello/test")
                .then().log().all()
                    .extract().response();

        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.getBody().jsonPath().getString("code"))
                .isEqualTo("COM000");
        assertThat(response.getBody().jsonPath().getString("message"))
                .isEqualTo("Success");
        assertThat(response.getBody().jsonPath().getString("data"))
                .isEqualTo(userId.toString());
    }
}
