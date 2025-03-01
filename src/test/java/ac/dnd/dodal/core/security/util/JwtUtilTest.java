package ac.dnd.dodal.core.security.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.jsonwebtoken.Claims;

import ac.dnd.dodal.IntegrationTest;
import ac.dnd.dodal.domain.user.enums.UserRole;
import ac.dnd.dodal.common.constant.Constants;

public class JwtUtilTest extends IntegrationTest {

    Long userId = 1L;
    String email = "test1@test.com";
    UserRole userRole = UserRole.USER;
    String profileImageUrl = "profile_image_url1";
    String deviceToken = "device_token1";
    String nickname = "test1";

    long millisecondsIn999Years = 9 * 365 * 24 * 60 * 60 * 1000L; // 31,536,000,000,000
    Long expirationPeriod = millisecondsIn999Years;

    @Test
    @DisplayName("Create JWT Token Success")
    public void create_jwt_token() {
        // when
        String token = jwtUtil.createToken(userId, userRole, expirationPeriod);

        // then
        Claims claims = jwtUtil.validateAndGetClaimsFromToken(token);
        assertThat(userId).isEqualTo(claims.get(Constants.USER_ID_CLAIM_NAME, Long.class));
        assertThat(userRole.name()).isEqualTo(
            claims.get(Constants.USER_ROLE_CLAIM_NAME, String.class));
    }
}
