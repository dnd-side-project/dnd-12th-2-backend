package ac.dnd.dodal.ui.auth.response;

import ac.dnd.dodal.domain.user.model.User;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record UserInfoResponseDto(
    String nickname, String email, String profileImageUrl, boolean checkOnboardingSuccess, JwtTokenDto jwtTokenDto) {
  public static UserInfoResponseDto fromUserEntity(User user, JwtTokenDto jwtTokenDto) {
    return UserInfoResponseDto.builder()
        .nickname(user.getNickname())
        .email(user.getEmail())
        .profileImageUrl(user.getProfileImageUrl())
            .checkOnboardingSuccess(true)
            .jwtTokenDto(jwtTokenDto)
        .build();
  }
}
