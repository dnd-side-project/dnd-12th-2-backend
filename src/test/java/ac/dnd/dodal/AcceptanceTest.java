package ac.dnd.dodal;

import io.restassured.RestAssured;

import org.junit.jupiter.api.BeforeAll;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public abstract class AcceptanceTest {

    protected static final int port = 9999;

    @BeforeAll
    public static void setUp() {
        RestAssured.port = port;
    }
}
