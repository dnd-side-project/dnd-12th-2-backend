package ac.dnd.dodal.ui.auth.response;

import ac.dnd.dodal.domain.user.enums.UserRole;
import ac.dnd.dodal.domain.user.model.User;
import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record DeleteUserInfoResponseDto(
    String nickname,
    String email,
        UserRole role,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    LocalDateTime deletedAt,
    String refreshToken
    ) {
  public static DeleteUserInfoResponseDto fromUserEntity(User user) {
    return DeleteUserInfoResponseDto.builder()
        .nickname(user.getNickname())
        .email(user.getEmail())
            .deletedAt(user.getDeletedAt())
            .createdAt(user.getCreatedAt())
            .updatedAt(user.getUpdatedAt())
            .role(user.getRole())
            .refreshToken(user.getRefreshToken())
        .build();
  }
}
