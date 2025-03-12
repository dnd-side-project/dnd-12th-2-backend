package ac.dnd.dodal.application.user.service;

import ac.dnd.dodal.application.user.repository.UserRepository;
import ac.dnd.dodal.application.user.usecase.UserQueryUseCase;
import ac.dnd.dodal.common.util.OAuth2Util;
import ac.dnd.dodal.core.security.util.JwtUtil;
import ac.dnd.dodal.domain.user.enums.UserRole;
import ac.dnd.dodal.domain.user.model.User;
import ac.dnd.dodal.ui.auth.request.AppleAuthorizationRequestDto;
import ac.dnd.dodal.ui.auth.request.KakaoAuthorizationRequestDto;
import ac.dnd.dodal.ui.auth.request.OAuthByAppleUserInfoRequestDto;
import ac.dnd.dodal.ui.auth.response.AppleIdTokenParsingDto;
import ac.dnd.dodal.ui.auth.response.JwtTokenDto;
import ac.dnd.dodal.ui.auth.response.KakaoUserInfoDto;
import ac.dnd.dodal.ui.auth.response.UserInfoResponseDto;
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
  private final AuthService authService;
  private final JwtUtil jwtUtil;
  private final OAuth2Util oAuth2Util;
  private final UserRepository userRepository;

  // 카카오 소셜 로그인
  public UserInfoResponseDto kakaoAuthSocialLogin(KakaoAuthorizationRequestDto kakaoAuthorizationRequestDto) {
    String accessToken = authService.refineToken(kakaoAuthorizationRequestDto.code());

    KakaoUserInfoDto kakaoUserInfoDto = getOAuth2UserInfo(accessToken);

    return processKakaoUserLogin(kakaoUserInfoDto, kakaoAuthorizationRequestDto.deviceToken());
  }

  // 애플 소셜 로그인
  public UserInfoResponseDto appleAuthSocialLogin(AppleAuthorizationRequestDto appleAuthorizationRequestDto) {
//    // 토큰 검증
//    JsonElement jsonElement =
//        oAuth2Util.verifyAuthorizationCode(appleAuthorizationRequestDto.code());

    // 사용자 정보 가져오기
    String id_token = appleAuthorizationRequestDto.code();
    String deviceToken = appleAuthorizationRequestDto.deviceToken();

    // id_token 파싱하기
    AppleIdTokenParsingDto appleIdTokenParsingDto =
        oAuth2Util.decodePayload(id_token, AppleIdTokenParsingDto.class);

    // 애플 로그인 응답 DTO 생성
    OAuthByAppleUserInfoRequestDto oAuthByAppleUserInfoRequestDto =
        new OAuthByAppleUserInfoRequestDto(
            appleIdTokenParsingDto.sub(), appleIdTokenParsingDto.email(), appleIdTokenParsingDto.email(), appleAuthorizationRequestDto.deviceToken());

    return processAppleUserLogin(oAuthByAppleUserInfoRequestDto);
  }

  // 애플 로그인 프로세스
  public UserInfoResponseDto processAppleUserLogin(OAuthByAppleUserInfoRequestDto oAuth2AppleUserInfo) {
    User user = userQueryUseCase.findByEmailAndRole(oAuth2AppleUserInfo.appleId(), UserRole.USER);
    if (user == null) {
      User newUser = registerNewAppleUser(oAuth2AppleUserInfo);
      return handleExistingUserLogin(newUser);
    }
    return handleExistingUserLogin(user);
  }

  // OAuth2 사용자 정보 가져오기
  private KakaoUserInfoDto getOAuth2UserInfo(String accessToken) {
    return oAuth2Util.getKakaoUserInfo(accessToken);
  }

  // 카카오 로그인 프로세스
  private UserInfoResponseDto processKakaoUserLogin(KakaoUserInfoDto kakaoUserInfo, String deviceToken) {
    User user = userQueryUseCase.findByEmailAndRole(kakaoUserInfo.email(), UserRole.USER);
    if (user == null) {
      // 새로운 사용자 등록
      User newUser = registerNewKakaoUser(kakaoUserInfo, deviceToken);
      return handleExistingUserLogin(newUser);
    }
    return handleExistingUserLogin(user);
  }

  // 기존 사용자 로그인 처리
  private UserInfoResponseDto handleExistingUserLogin(User user) {
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

  private User registerNewAppleUser(OAuthByAppleUserInfoRequestDto appleIdTokenParsingDto) {
    // 탈퇴한 이력이 있는 사용자인지 확인
    User withdrawnUser = checkWithdrawnUser(appleIdTokenParsingDto.appleId());

    if (withdrawnUser != null && withdrawnUser.getDeletedAt() != null) {
      withdrawnUser.reactivateDeletedUser();
      return withdrawnUser;
    }
    else {
      User newUser =
          new User(
              appleIdTokenParsingDto.email(),
              null,
              appleIdTokenParsingDto.deviceToken(),
              appleIdTokenParsingDto.appleId(),
              UserRole.USER);
      return userRepository.save(newUser);
    }
  }

  private User registerNewKakaoUser(KakaoUserInfoDto kakaoUserInfo, String deviceToken) {
    User newUser =
        new User(kakaoUserInfo.nickname(), kakaoUserInfo.profileImageUrl(), deviceToken, kakaoUserInfo.email(), UserRole.USER);
    return userRepository.save(newUser);
  }

  private User checkWithdrawnUser(String email) {
    return userQueryUseCase.findByEmailAndRole(email, UserRole.USER);
  }
}
