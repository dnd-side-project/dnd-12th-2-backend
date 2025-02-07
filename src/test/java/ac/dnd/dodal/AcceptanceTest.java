package ac.dnd.dodal;

import java.util.Map;

import io.restassured.RestAssured;

import org.junit.jupiter.api.BeforeAll;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ac.dnd.dodal.ui.fixture.UIFixture;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public abstract class AcceptanceTest {

    protected static final int port = 9999;

    protected static String accessToken;
    protected static String refreshToken;

    protected static Map<String, Object> authorizationHeader;
    protected static Map<String, Object> refreshAuthorizationHeader;
    protected static Map<String, Object> emptyAuthorizationHeader;
    protected static Map<String, Object> unauthorizedAuthorizationHeader;
    protected static Map<String, Object> unauthorizedRefreshAuthorizationHeader;

    @BeforeAll
    public static void setUp() {
        RestAssured.port = port;
        // TODO: 로그인 테스트 추가
        // Map<String, Object> data = RestAssured.given().log().all()
        //     .when()
        //         .post("/auth/login")
        //     .then().log().all()
        //         .extract()
        //     .jsonPath()
        //         .get("data");

        // accessToken = (String) data.get("accessToken");
        // refreshToken = (String) data.get("refreshToken");

        accessToken = "accessToken";
        refreshToken = "refreshToken";

        authorizationHeader = UIFixture.createAuthorizationHeader(accessToken);
        refreshAuthorizationHeader = UIFixture.createRefreshAuthorizationHeader(refreshToken);
        emptyAuthorizationHeader = UIFixture.createEmptyHeader();
        unauthorizedAuthorizationHeader = UIFixture.createUnauthorizedHeader();
        unauthorizedRefreshAuthorizationHeader = UIFixture.createUnauthorizedRefreshHeader();
    }
}
