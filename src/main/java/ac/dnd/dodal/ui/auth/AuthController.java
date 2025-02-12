package ac.dnd.dodal.ui.auth;

import ac.dnd.dodal.application.user.service.AuthLoginService;
import ac.dnd.dodal.application.user.service.AuthSignUpService;
import ac.dnd.dodal.common.constant.Constants;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.ui.auth.request.AppleAuthorizationRequestDto;
import ac.dnd.dodal.ui.auth.request.OAuthUserInfoRequestDto;
import ac.dnd.dodal.ui.auth.request.OAuthUserInfoRequestDto;
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
  private final AuthSignUpService authSignUpService;

  // 카카오 소셜 로그인
  @PostMapping("/login/kakao")
  public ApiResponse<?> authKakaoSocialLogin(
      @NotNull @RequestHeader(Constants.AUTHORIZATION_HEADER) String accessToken) {
    log.info("accessToken : {}", accessToken); // 'bearer ' 제거 필요
    return ApiResponse.success(authLoginService.kakaoAuthSocialLogin(accessToken));
  }

  // 애플 소셜 로그인
  @PostMapping("/login/apple")
  public ApiResponse<?> authAppleSocialLogin(
      @RequestBody @Valid AppleAuthorizationRequestDto appleAuthorizationRequestDto) {
    return ApiResponse.success(authLoginService.appleAuthSocialLogin(appleAuthorizationRequestDto));
  }

  // 기타 회원가입 API
  @PostMapping("/sign-up")
  public ApiResponse<?> authSignUp(
      @RequestBody @Valid OAuthUserInfoRequestDto authSignUpRequestDto) {
    return ApiResponse.success(authSignUpService.handleSignUp(authSignUpRequestDto));
  }
}
