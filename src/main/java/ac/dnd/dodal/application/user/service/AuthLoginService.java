package ac.dnd.dodal.application.user.service;

import ac.dnd.dodal.application.user.repository.UserRepository;
import ac.dnd.dodal.application.user.usecase.UserQueryUseCase;
import ac.dnd.dodal.common.util.OAuth2Util;
import ac.dnd.dodal.core.security.util.JwtUtil;
import ac.dnd.dodal.domain.user.enums.UserExceptionCode;
import ac.dnd.dodal.domain.user.enums.UserRole;
import ac.dnd.dodal.domain.user.exception.UserNotFoundException;
import ac.dnd.dodal.domain.user.model.User;
import ac.dnd.dodal.ui.auth.request.AppleAuthorizationRequestDto;
import ac.dnd.dodal.ui.auth.request.OAuthByAppleUserInfoRequestDto;
import ac.dnd.dodal.ui.auth.response.AppleIdTokenParsingDto;
import ac.dnd.dodal.ui.auth.response.JwtTokenDto;
import ac.dnd.dodal.ui.auth.response.KakaoUserInfoDto;
import ac.dnd.dodal.ui.auth.response.UserInfoResponseDto;
import com.google.gson.JsonElement;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthLoginService {

  private final UserQueryUseCase userQueryUseCase;
  private final JwtUtil jwtUtil;
  private final OAuth2Util oAuth2Util;
  private final UserRepository userRepository;

  // 카카오 소셜 로그인
  public Object kakaoAuthSocialLogin(String token) {
    String accessToken = AuthService.refineToken(token);

    KakaoUserInfoDto kakaoUserInfoDto = getOAuth2UserInfo(accessToken);

    return processKakaoUserLogin(kakaoUserInfoDto);
  }

  // 애플 소셜 로그인
  public Object appleAuthSocialLogin(AppleAuthorizationRequestDto appleAuthorizationRequestDto) {
    // 토큰 검증
    JsonElement jsonElement =
        oAuth2Util.verifyAuthorizationCode(appleAuthorizationRequestDto.code());

    // 사용자 정보 가져오기
    String id_token = jsonElement.getAsJsonObject().get("id_token").getAsString();
    String deviceToken = appleAuthorizationRequestDto.deviceToken();

    // id_token 파싱하기
    AppleIdTokenParsingDto appleIdTokenParsingDto =
        oAuth2Util.decodePayload(id_token, AppleIdTokenParsingDto.class);

    // 애플의 경우 이메일에는 sub, name에는 email이 들어가 있음
    User user = userQueryUseCase.findByEmail(appleIdTokenParsingDto.sub())
            .orElseGet(() -> registerNewUser(appleIdTokenParsingDto, deviceToken));

    // 애플 로그인 응답 DTO 생성
    OAuthByAppleUserInfoRequestDto oAuthByAppleUserInfoRequestDto =
        new OAuthByAppleUserInfoRequestDto(
            appleIdTokenParsingDto.sub(), user.getEmail(), user.getNickname(), user.getId());

    return processAppleUserLogin(oAuthByAppleUserInfoRequestDto);
  }

  // 애플 로그인 프로세스
  public Object processAppleUserLogin(OAuthByAppleUserInfoRequestDto oAuth2AppleUserInfo) {
    User user = userQueryUseCase.findByIdAndRole(oAuth2AppleUserInfo.userId(), UserRole.USER);
    if (user == null) {
      throw new UserNotFoundException(UserExceptionCode.NOT_FOUND_USER);
    }
    return handleExistingUserLogin(user);
  }

  // OAuth2 사용자 정보 가져오기
  private KakaoUserInfoDto getOAuth2UserInfo(String accessToken) {
    return oAuth2Util.getKakaoUserInfo(accessToken);
  }

  // 카카오 로그인 프로세스
  private Object processKakaoUserLogin(KakaoUserInfoDto kakaoUserInfo) {
    User user = userQueryUseCase.findByEmailAndRole(kakaoUserInfo.email(), UserRole.USER);
    if (user == null) {
      throw new UserNotFoundException(UserExceptionCode.NOT_FOUND_USER, "사용자를 찾을 수 없습니다.");
    }
    return handleExistingUserLogin(user);
  }

  // 기존 사용자 로그인 처리
  private Object handleExistingUserLogin(User user) {
    // 토큰 생성 후 업데이트
    JwtTokenDto jwtTokenDto = jwtUtil.generateToken(user.getId(), UserRole.USER);
    user.updateRefreshToken(jwtTokenDto.refreshToken());

    // 로그인 후 필요한 데이터를 생성하고 반환
    return buildUserInfoResponse(user, jwtTokenDto);
  }

  private UserInfoResponseDto buildUserInfoResponse(User user, JwtTokenDto jwtTokenDto) {

    log.info(user.getEmail(), user.getRefreshToken());
    return UserInfoResponseDto.fromUserEntity(user, jwtTokenDto);
  }

  private User registerNewUser(AppleIdTokenParsingDto appleIdTokenParsingDto, String deviceToken) {
    User newUser = new User(appleIdTokenParsingDto.email(), null, deviceToken, appleIdTokenParsingDto.sub(), UserRole.USER);
    return userRepository.save(newUser);
  }
}
