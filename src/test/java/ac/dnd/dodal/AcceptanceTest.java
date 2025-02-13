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

    protected static final Long userId = 1L;
    protected static final String accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJ1aWQiOjEsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzM5MzA4OTc1LCJleHAiOjE3NDAxODc4NTl9.7nH4G9T2PW9AI7JTCm7RteiechdLWsoeWanh4kX-Yt6UQ4qQVhohXlpA7DYR9fZZwjiyn7GLN73m1LXsNT1Djw";
    protected static final String refreshToken =
            "eyJhbGciOiJIUzUxMiJ9.eyJ1aWQiOjEsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzM5MzA4OTc1LCJleHAiOjE3NDAxODc4NTl9.7nH4G9T2PW9AI7JTCm7RteiechdLWsoeWanh4kX-Yt6UQ4qQVhohXlpA7DYR9fZZwjiyn7GLN73m1LXsNT1Djw";

    protected static final Long achievedGoalId = 3L;
    protected static final Long deletedGoalId = 2L;
    protected static final Long unachievedGoalId = 1L;
    protected static final Long goalId = 1L;

    protected static final Long planId = 1L;
    protected static final Long uncompletedPlanId = 15L;
    protected static final Long latestSuccessPlanIdWithHistory_1 = 17L;
    protected static final Long latestFailurePlanIdWithHistory_2 = 34L;

    protected static final Long planHistoryId = 1L;
    protected static final Long lastSuccessPlanHistoryId = 1L;
    protected static final Long lastFailurePlanHistoryId = 2L;

    protected static final Map<String, Object> authorizationHeader
        = UIFixture.createAuthorizationHeader(accessToken);
    protected static final Map<String, Object> refreshAuthorizationHeader
        = UIFixture.createRefreshAuthorizationHeader(refreshToken);
    protected static final Map<String, Object> emptyAuthorizationHeader
        = UIFixture.createEmptyHeader();
    protected static final Map<String, Object> unauthorizedAuthorizationHeader
        = UIFixture.createUnauthorizedHeader();
    protected static final Map<String, Object> unauthorizedRefreshAuthorizationHeader
        = UIFixture.createUnauthorizedRefreshHeader();

    @BeforeAll
    public static void setUp() {
        RestAssured.port = port;
    }
}
