package ac.dnd.dodal.acceptance.hello;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.response.Response;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloAcceptanceTest {

    @LocalServerPort
    private int port;

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
