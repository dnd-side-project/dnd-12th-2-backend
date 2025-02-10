package ac.dnd.dodal.application.user.service;

import ac.dnd.dodal.application.user.usecase.UserQueryUseCase;
import ac.dnd.dodal.common.util.OAuth2Util;
import ac.dnd.dodal.core.security.util.JwtUtil;
import ac.dnd.dodal.domain.user.enums.UserExceptionCode;
import ac.dnd.dodal.domain.user.enums.UserRole;
import ac.dnd.dodal.domain.user.exception.UserNotFoundException;
import ac.dnd.dodal.domain.user.model.User;
import ac.dnd.dodal.ui.user.response.JwtTokenDto;
import ac.dnd.dodal.ui.user.response.KakaoUserInfoDto;
import ac.dnd.dodal.ui.user.response.UserInfoResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthKakaoLoginService {

    private final AuthService authService;
    private final UserQueryUseCase userQueryUseCase;
    private final JwtUtil jwtUtil;
    private final OAuth2Util oAuth2Util;

    // 소셜 로그인
    public Object authSocialLogin(String token) {
        String accessToken = authService.refineToken(token);

        KakaoUserInfoDto kakaoUserInfoDto = getOAuth2UserInfo(accessToken);

        return processUserLogin(kakaoUserInfoDto);
    }

    // OAuth2 사용자 정보 가져오기
    private KakaoUserInfoDto getOAuth2UserInfo(String accessToken) {
       return oAuth2Util.getKakaoUserInfo(accessToken);
    }

    // 로그인 프로세스
    private Object processUserLogin(KakaoUserInfoDto kakaoUserInfo) {
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
        return UserInfoResponseDto.fromUserEntity(
                user,
                jwtTokenDto
        );
    }
}
