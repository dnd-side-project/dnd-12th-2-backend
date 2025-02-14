package ac.dnd.dodal.application.user.service;

import ac.dnd.dodal.application.user.usecase.UserQueryUseCase;
import ac.dnd.dodal.common.constant.Constants;
import ac.dnd.dodal.common.enums.CommonResultCode;
import ac.dnd.dodal.common.exception.BadRequestException;
import ac.dnd.dodal.core.security.enums.SecurityExceptionCode;
import ac.dnd.dodal.core.security.util.JwtUtil;
import ac.dnd.dodal.domain.user.enums.UserExceptionCode;
import ac.dnd.dodal.domain.user.model.User;
import ac.dnd.dodal.ui.auth.response.JwtTokenDto;
import ac.dnd.dodal.ui.auth.response.UserInfoResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

  private final JwtUtil jwtUtil;
  private final UserQueryUseCase userQueryUseCase;

  public String refineToken(String accessToken) {
    return accessToken.startsWith(Constants.BEARER_PREFIX)
        ? accessToken.substring(Constants.BEARER_PREFIX.length())
        : accessToken;
  }

  public UserInfoResponseDto refresh(String token) {
    String refreshToken = this.refineToken(token);

    Long userId = jwtUtil.getUserIdFromToken(refreshToken);
    User user = userQueryUseCase.findById(userId);

    if (!user.getRefreshToken().equals(refreshToken)) {
      throw new BadRequestException(SecurityExceptionCode.TOKEN_UNKNOWN_ERROR);
    }

    JwtTokenDto jwtTokenDto = jwtUtil.generateToken(user.getId(), user.getRole());
    // 액세스 토큰을 다시 발급받을 때 refreshToken도 같이 업데이트 하여 장기간 접속하지 않는 한 로그인 유지
    user.updateRefreshToken(jwtTokenDto.refreshToken());

    return UserInfoResponseDto.fromUserEntity(user, jwtTokenDto);
  }
}
