package ac.dnd.dodal.core.config.security.info;

import ac.dnd.dodal.domain.user.enums.E_user_role;
import lombok.Builder;

@Builder
public record JwtUserInfo(Long id, E_user_role role) {
}
