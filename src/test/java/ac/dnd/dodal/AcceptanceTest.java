package ac.dnd.dodal;

import java.util.Map;

import jakarta.transaction.Transactional;

import io.restassured.RestAssured;

import org.junit.jupiter.api.BeforeAll;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import ac.dnd.dodal.ui.fixture.UIFixture;

@Transactional
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public abstract class AcceptanceTest {

    protected static final int port = 9999;

    protected static final String userId = "1";
    protected static final String accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJ1aWQiOjEsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzM5MzA4OTc1LCJleHAiOjE3NDAxODc4NTl9.7nH4G9T2PW9AI7JTCm7RteiechdLWsoeWanh4kX-Yt6UQ4qQVhohXlpA7DYR9fZZwjiyn7GLN73m1LXsNT1Djw";
    protected static final String refreshToken = "eyJhbGciOiJIUzUxMiJ9.eyJ1aWQiOjEsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzM5MzA4OTc1LCJleHAiOjE3NDAxODc4NTl9.7nH4G9T2PW9AI7JTCm7RteiechdLWsoeWanh4kX-Yt6UQ4qQVhohXlpA7DYR9fZZwjiyn7GLN73m1LXsNT1Djw";

    protected static Map<String, Object> authorizationHeader;
    protected static Map<String, Object> refreshAuthorizationHeader;
    protected static Map<String, Object> emptyAuthorizationHeader;
    protected static Map<String, Object> unauthorizedAuthorizationHeader;
    protected static Map<String, Object> unauthorizedRefreshAuthorizationHeader;

    @BeforeAll
    public static void setUp() {
        RestAssured.port = port;

        authorizationHeader = UIFixture.createAuthorizationHeader(accessToken);
        refreshAuthorizationHeader = UIFixture.createRefreshAuthorizationHeader(refreshToken);
        emptyAuthorizationHeader = UIFixture.createEmptyHeader();
        unauthorizedAuthorizationHeader = UIFixture.createUnauthorizedHeader();
        unauthorizedRefreshAuthorizationHeader = UIFixture.createUnauthorizedRefreshHeader();
    }
}
