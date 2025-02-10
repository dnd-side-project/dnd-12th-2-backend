package ac.dnd.dodal.application.user.service;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import ac.dnd.dodal.application.user.dto.AuthSignUpFixture;
import ac.dnd.dodal.common.constant.Constants;
import ac.dnd.dodal.core.security.util.JwtUtil;
import ac.dnd.dodal.domain.user.enums.UserRole;
import ac.dnd.dodal.domain.user.model.User;
import ac.dnd.dodal.ui.user.response.JwtTokenDto;
import io.jsonwebtoken.Claims;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AuthRelatedServiceTest {

  @Mock
  private JwtUtil jwtUtil;  // Mock 객체로 변경

  @InjectMocks
  private AuthService authService; // Mock 객체로 변경


  private User testUser;
  private String testAccessToken;
  private String testRefreshToken;

  @BeforeEach
  void setUp() {
    testUser = AuthSignUpFixture.signUpUser();
    testAccessToken = "mockAccessToken";
    testRefreshToken = "mockRefreshToken";

    // lenient() 적용 -> 사용되지 않는 Stubbing이 있어도 예외 발생하지 않음
    lenient().when(jwtUtil.generateToken(testUser.getId(), UserRole.USER))
            .thenReturn(new JwtTokenDto(testAccessToken, testRefreshToken));

    Claims mockClaims = mock(Claims.class);
    lenient().when(mockClaims.get(Constants.USER_EMAIL_CLAIM_NAME, String.class))
            .thenReturn(testUser.getEmail());
    lenient().when(mockClaims.getExpiration()).thenReturn(new Date(System.currentTimeMillis() + 10000));

    lenient().when(jwtUtil.validateAndGetClaimsFromToken(testAccessToken))
            .thenReturn(mockClaims);
  }

  @Test
  @DisplayName("로그인 시 사용자 Jwt 토큰 생성 성공")
  void jwtTokenShouldBeGenerated() {
    // given
    String originalRefreshToken = testUser.getRefreshToken();

    // when
    JwtTokenDto newToken = jwtUtil.generateToken(testUser.getId(), UserRole.USER);
    testUser.updateRefreshToken(newToken.refreshToken());

    // then
    assertThat(originalRefreshToken).isNotEqualTo(testUser.getRefreshToken());

    // JWT 디코딩 확인
    Claims claims = jwtUtil.validateAndGetClaimsFromToken(newToken.accessToken());
    assertThat(claims.get(Constants.USER_EMAIL_CLAIM_NAME, String.class))
            .isEqualTo(testUser.getEmail());
    assertThat(claims.getExpiration()).isAfter(new Date()); // 만료 시간이 현재보다 미래인지 확인
  }

  @Test
  @DisplayName("refineToken 메서드 테스트")
  void refineTokenTest() {
    // given
    String accessToken = "Bearer testToken";

    // when
    String refinedToken = authService.refineToken(accessToken);

    // then
    assertThat(refinedToken).isEqualTo("testToken");
  }
}