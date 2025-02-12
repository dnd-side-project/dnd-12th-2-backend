package ac.dnd.dodal.ui.auth.response;

public record AppleUserInfoResponseDto(
        String nickname, String email, String appleId, JwtTokenDto jwtTokenDto)
{
}
