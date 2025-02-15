package ac.dnd.dodal.ui.auth.request;

import jakarta.validation.constraints.NotBlank;

public record KakaoAuthorizationRequestDto(
        @NotBlank String code,
        @NotBlank String deviceToken
) {
}
