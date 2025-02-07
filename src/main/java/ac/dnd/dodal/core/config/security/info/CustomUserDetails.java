package ac.dnd.dodal.core.config.security.info;

import ac.dnd.dodal.common.constant.Constants;
import ac.dnd.dodal.domain.user.enums.E_user_role;
import ac.dnd.dodal.domain.user.model.Users;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomUserDetails implements UserDetails {
    @Getter
    private final Long id;
    @Getter
    private final String email;

    @Getter
    private final E_user_role role;
    private final Collection<? extends GrantedAuthority> authorities;

    public static CustomUserDetails create(Users user) {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(Constants.ROLE_PREFIX + user.getRole());
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);
        return CustomUserDetails.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .authorities(authorities)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
