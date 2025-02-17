package ac.dnd.dodal.ui.fixture;

import java.util.Map;

public class UIFixture {

    public static Map<String, Object> createAuthorizationHeader(String accessToken) {
        return Map.of("Authorization", "Bearer " + accessToken);
    }

    public static Map<String, Object> createRefreshAuthorizationHeader(String refreshToken) {
        return Map.of("Authorization", "Bearer " + refreshToken);
    }

    public static Map<String, Object> createUnauthorizedHeader() {
        return Map.of("Authorization", "Bearer " + "invalid-access-token");
    }

    public static Map<String, Object> createUnauthorizedRefreshHeader() {
        return Map.of("refreshToken", "Bearer " + "invalid-refresh-token");
    }

    public static Map<String, Object> createEmptyHeader() {
        return Map.of();
    }
}
