package ac.dnd.dodal.core.security.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import ac.dnd.dodal.IntegrationTest;
import ac.dnd.dodal.domain.user.enums.UserRole;

public class JwtUtilTest extends IntegrationTest {

    @Autowired
    JwtUtil jwtUtil;

    Long userId = 1L;
    String email = "test1@test.com";
    UserRole userRole = UserRole.USER;
    String profileImageUrl = "profile_image_url1";
    String deviceToken = "device_token1";
    String nickname = "test1";

    long millisecondsIn999Years = 999 * 365 * 24 * 60 * 60 * 1000; // 31,536,000,000,000
    Long expirationPeriod = millisecondsIn999Years;

    @Test
    @DisplayName("Create JWT Token Success")
    public void create_jwt_token() {
        String token = jwtUtil.createToken(userId, userRole, expirationPeriod);
        System.out.println("Create JWT Token (999Years) = " + token);

        assertThat(userId).isEqualTo(jwtUtil.getUserIdFromToken(token));
    }
}
