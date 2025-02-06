package ac.dnd.dodal.config.security;

import ac.dnd.dodal.domain.user.enums.E_user_role;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private Long userId;
    private E_user_role role;

    public JwtAuthenticationToken(Collection<? extends GrantedAuthority> authorities, Long id, E_user_role role) {
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
