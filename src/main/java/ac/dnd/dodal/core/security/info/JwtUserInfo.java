package ac.dnd.dodal.core.security.info;

import ac.dnd.dodal.domain.user.enums.UserRole;
import lombok.Builder;

@Builder
public record JwtUserInfo(
        Long id, UserRole role
) {
}