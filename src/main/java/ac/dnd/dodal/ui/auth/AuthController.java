package ac.dnd.dodal.ui.auth;

import ac.dnd.dodal.application.user.service.AuthLoginService;
import ac.dnd.dodal.application.user.service.AuthService;
import ac.dnd.dodal.common.constant.Constants;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.ui.auth.request.AppleAuthorizationRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthLoginService authLoginService;
  private final AuthService authService;

  // 카카오 소셜 로그인
  @PostMapping("/login/kakao")
  public ApiResponse<?> authKakaoSocialLogin(
      @NotNull @RequestHeader(Constants.AUTHORIZATION_HEADER) String accessToken,
      @NotNull @RequestHeader("Device-Token") String deviceToken) {
    log.info("accessToken : {}", accessToken); // 'bearer ' 제거 필요
    return ApiResponse.success(authLoginService.kakaoAuthSocialLogin(accessToken, deviceToken));
  }

  // 애플 소셜 로그인
  @PostMapping("/login/apple")
  public ApiResponse<?> authAppleSocialLogin(
      @RequestBody @Valid AppleAuthorizationRequestDto appleAuthorizationRequestDto) {
    return ApiResponse.success(authLoginService.appleAuthSocialLogin(appleAuthorizationRequestDto));
  }

  // 액세스 토큰 재발급 및 리프레시 토큰 업데이트 API
  @PostMapping("/refresh")
  public ApiResponse<?> refresh(
      @NotNull @RequestHeader(Constants.AUTHORIZATION_HEADER) String refreshToken) {
    return ApiResponse.success(authService.refresh(refreshToken));
  }
}
