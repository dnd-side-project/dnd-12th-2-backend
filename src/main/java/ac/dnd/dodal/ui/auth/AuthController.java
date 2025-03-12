package ac.dnd.dodal.ui.auth;

import ac.dnd.dodal.application.user.service.AuthLoginService;
import ac.dnd.dodal.application.user.service.AuthService;
import ac.dnd.dodal.application.user.usecase.UserCommandUseCase;
import ac.dnd.dodal.common.annotation.UserId;
import ac.dnd.dodal.common.constant.Constants;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.ui.auth.request.AppleAuthorizationRequestDto;
import ac.dnd.dodal.ui.auth.request.KakaoAuthorizationRequestDto;
import ac.dnd.dodal.ui.auth.response.UserInfoResponseDto;
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
  private final UserCommandUseCase userCommandUseCase;

  // 카카오 소셜 로그인
  @PostMapping("/login/kakao")
  public ApiResponse<UserInfoResponseDto> authKakaoSocialLogin(
          @RequestBody @Valid KakaoAuthorizationRequestDto kakaoAuthorizationRequestDto) {
    return ApiResponse.success(authLoginService.kakaoAuthSocialLogin(kakaoAuthorizationRequestDto));
  }

  // 애플 소셜 로그인
  @PostMapping("/login/apple")
  public ApiResponse<UserInfoResponseDto> authAppleSocialLogin(
      @RequestBody @Valid AppleAuthorizationRequestDto appleAuthorizationRequestDto) {
    return ApiResponse.success(authLoginService.appleAuthSocialLogin(appleAuthorizationRequestDto));
  }

  // 액세스 토큰 재발급 및 리프레시 토큰 업데이트 API
  @PostMapping("/refresh")
  public ApiResponse<UserInfoResponseDto> refresh(
      @NotNull @RequestHeader(Constants.AUTHORIZATION_HEADER) String refreshToken) {
    return ApiResponse.success(authService.refresh(refreshToken));
  }

  @DeleteMapping("/delete")
    public ApiResponse<?> softDeleteUser(
          @UserId Long userId) {
        return ApiResponse.success(userCommandUseCase.delete(userId));
    }
}
