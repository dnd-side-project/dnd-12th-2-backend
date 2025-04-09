package ac.dnd.dodal.common.constant;

import java.util.List;

public class Constants {

    public static final String BEARER_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String REAUTHORIZATION = "refreshToken";
    public static final String ROLE_PREFIX = "ROLE_";
    public static final String USER_ROLE_CLAIM_NAME = "role";
    public static final String USER_ID_CLAIM_NAME = "uid";
    public static final String USER_EMAIL_CLAIM_NAME = "email";

    // 소셜 로그인 관련 상수
    public static final String APPLE_PUBLIC_KEYS_URL = "https://appleid.apple.com/auth/keys";
    public static final String KAKAO_RESOURCE_SERVER_URL = "https://kapi.kakao.com/v2/user/me";
    public static final String APPLE_TOKEN_URL = "https://appleid.apple.com/auth/token";
    public static final String APPLE_REVOKE_URL = "https://appleid.apple.com/auth/revoke";

    /**
     * Urls which don't need authentication
     * but need to be filtered
     * 
     * @see JwtAuthenticationFilter
     */
    public static final List<String> NO_NEED_AUTH_URLS = List.of(

            // 회원가입
            "/api/auth/sign-up",
            // 로그인
            "/api/auth/sign-in",
            "/api/auth/login/**",
            "/api/auth/login/kakao",
            "/api/auth/login/naver",
            "/api/auth/login/google",
            "/api/auth/login/apple"
    );

    /**
     * Urls which bypass security filter so that it can be accessed without authentication
     * Difference from NO_NEED_AUTH_URLS, NO_NEED_AUTH_URLS is 
     * that they don't need to be authenticated but need to be filtered.
     * 
     * @see JwtAuthenticationFilter
     */
    public static final List<String> BYPASS_URLS = List.of(
            // 
            "/hello",
            // 모니터링 
            "/actuator/**",
            // 피드백 데이터 조회
            "/api/feedbacks"
    );

    public static final String CONTENT_TYPE = "Content-Type";

}
