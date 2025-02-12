package ac.dnd.dodal.ui.user.response;

public record AppleUserInfoResponseDto(
        String nickname, String email, String appleId, JwtTokenDto jwtTokenDto)
{
}
