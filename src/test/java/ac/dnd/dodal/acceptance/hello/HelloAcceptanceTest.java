package ac.dnd.dodal.acceptance.hello;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.response.Response;

import ac.dnd.dodal.AcceptanceTest;

public class HelloAcceptanceTest extends AcceptanceTest {

    @DisplayName("Hello API Test")
    @Test
    public void hello() {
        Response response = given().log().all()
            .port(port)
        .when()
            .get("/hello")
        .then().log().all()
            .extract()
            .response();

        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.getBody().jsonPath().getString("data")).isEqualTo("Hello, Dodal World!");
    }
}
