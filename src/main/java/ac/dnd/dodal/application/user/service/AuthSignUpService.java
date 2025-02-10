package ac.dnd.dodal.application.user.service;

import ac.dnd.dodal.application.user.usecase.UserCommandUseCase;
import ac.dnd.dodal.application.user.usecase.UserQueryUseCase;

import ac.dnd.dodal.core.security.util.JwtUtil;
import ac.dnd.dodal.domain.user.enums.UserRole;
import ac.dnd.dodal.domain.user.model.User;
import ac.dnd.dodal.ui.user.request.OAuthUserInfoRequestDto;
import ac.dnd.dodal.ui.user.response.JwtTokenDto;
import ac.dnd.dodal.ui.user.response.UserInfoResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthSignUpService {

  private final UserQueryUseCase userQueryUseCase;
  private final UserCommandUseCase userCommandUseCase;
  private final JwtUtil jwtUtil;

  public UserInfoResponseDto handleSignUp(OAuthUserInfoRequestDto authSignUpRequestDto) {
    String email = authSignUpRequestDto.email();
    String nickname = authSignUpRequestDto.nickname();

    userQueryUseCase.checkDuplicatedEmail(email);
    userQueryUseCase.checkDuplicatedNickname(nickname);

    User newUser = userCommandUseCase.createUserBySocialSignUp(authSignUpRequestDto);

    return provideNewJwtToken(newUser);
  }

  private UserInfoResponseDto provideNewJwtToken(User user) {
    // 토큰 생성 후 업데이트
    JwtTokenDto jwtTokenDto = jwtUtil.generateToken(user.getId(), UserRole.USER);
    user.updateRefreshToken(jwtTokenDto.refreshToken());

    // 로그인 후 필요한 데이터를 생성하고 반환
    return buildUserInfoResponse(user, jwtTokenDto);
  }

  private UserInfoResponseDto buildUserInfoResponse(User user, JwtTokenDto JwtTokenDto) {
    return UserInfoResponseDto.fromUserEntity(user, JwtTokenDto);
  }
}
