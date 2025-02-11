package ac.dnd.dodal.core.security;

import ac.dnd.dodal.domain.user.enums.UserRole;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private Long userId;
    private UserRole role;

    public JwtAuthenticationToken(Collection<? extends GrantedAuthority> authorities, Long id, UserRole role) {
        super(authorities);
        this.userId = id;
        this.role = role;
    }

    @Override
    public Object getCredentials() {
        return this.role;
    }

    @Override
    public Object getPrincipal() {
        return this.userId;
    }
}
