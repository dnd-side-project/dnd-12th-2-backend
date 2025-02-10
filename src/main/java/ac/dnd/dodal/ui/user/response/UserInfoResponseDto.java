package ac.dnd.dodal.ui.user.response;

import ac.dnd.dodal.domain.user.model.User;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record UserInfoResponseDto(
    String nickname, String email, String profileImageUrl, JwtTokenDto jwtTokenDto) {
  public static UserInfoResponseDto fromUserEntity(User user, JwtTokenDto jwtTokenDto) {
    return UserInfoResponseDto.builder()
        .nickname(user.getNickname())
        .email(user.getEmail())
        .profileImageUrl(user.getProfileImageUrl())
        .jwtTokenDto(jwtTokenDto)
        .build();
  }
}
