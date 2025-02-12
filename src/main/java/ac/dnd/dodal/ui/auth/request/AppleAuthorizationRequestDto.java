package ac.dnd.dodal.ui.auth.request;

import jakarta.validation.constraints.NotBlank;

public record AppleAuthorizationRequestDto(
    @NotBlank String code,
    @NotBlank String deviceToken
) {
}