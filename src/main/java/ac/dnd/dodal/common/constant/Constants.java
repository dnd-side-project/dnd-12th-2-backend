package ac.dnd.dodal.common.constant;

import java.net.URI;
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


    public static final List<String> NO_NEED_AUTH_URLS = List.of(

            //로그인 URL
            //Swagger URL
            //로그인 없이 둘러볼 수 있는 URL
            "/hello",
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

    public static final String CONTENT_TYPE = "Content-Type";

}
