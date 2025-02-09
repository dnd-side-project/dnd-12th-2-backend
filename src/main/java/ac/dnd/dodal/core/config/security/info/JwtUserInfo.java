package ac.dnd.dodal.core.config.security.info;

import ac.dnd.dodal.domain.user.enums.EUserRole;
import lombok.Builder;

@Builder
public record JwtUserInfo(
        Long id, EUserRole role
) {
}