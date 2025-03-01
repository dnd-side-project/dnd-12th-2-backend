package ac.dnd.dodal;

import java.time.LocalDate;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.restassured.RestAssured;

import org.junit.jupiter.api.BeforeAll;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import ac.dnd.dodal.ui.fixture.UIFixture;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public abstract class AcceptanceTest {

    protected static final Logger log = LoggerFactory.getLogger(AcceptanceTest.class);

    protected static final int port = 9999;

    protected static final Long userId = 1L;
    protected static final String userEmail = "test1@test.com";
    protected static final String accessToken =
            "eyJhbGciOiJIUzUxMiJ9.eyJ1aWQiOjEsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzQwODIwNTc0LCJleHAiOjIwMjQ2NDQ1NzR9.YpyYaOtaNuz85cGeacU2OZScBz6jdlV5V8AxxBTSCF7Nis1CcHmHvp5bPeNOEvO219L7ce5yrvkHbUrG71uOIg";
    protected static final String refreshToken =
            "eyJhbGciOiJIUzUxMiJ9.eyJ1aWQiOjEsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzQwODIwNTc0LCJleHAiOjIwMjQ2NDQ1NzR9.YpyYaOtaNuz85cGeacU2OZScBz6jdlV5V8AxxBTSCF7Nis1CcHmHvp5bPeNOEvO219L7ce5yrvkHbUrG71uOIg";

    protected static final Long userId2 = 2L;
    protected static final String userEmail2 = "test2@test.com";
    protected static final String accessToken2 = "eyJhbGciOiJIUzUxMiJ9.eyJ1aWQiOjIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzQwMDAwODg3LCJleHAiOjE3NDA4Nzk3NzF9.B2TGyw8e291ziSz4YE2PBcBXD-zWrHE2ut50Ye5dSQnfY26H20Fi1-riXBU-JBNHTX0O6XSg1PjahZ-x8tD8YA";

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

    protected static final LocalDate date = LocalDate.of(2024, 4, 1);

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
    protected static final Map<String, Object> authorizationHeader2
        = UIFixture.createAuthorizationHeader(accessToken2);

    @BeforeAll
    public static void setUp() {
        RestAssured.port = port;
    }
}
