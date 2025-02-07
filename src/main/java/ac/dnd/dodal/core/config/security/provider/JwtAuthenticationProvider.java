package ac.dnd.dodal.core.config.security.provider;

import ac.dnd.dodal.core.config.security.JwtAuthenticationToken;
import ac.dnd.dodal.core.config.security.info.CustomUserDetails;
import ac.dnd.dodal.core.config.security.info.JwtUserInfo;
import ac.dnd.dodal.core.config.security.service.CustomUserDetailsService;
import ac.dnd.dodal.domain.user.enums.E_user_role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof JwtAuthenticationToken) {
            JwtUserInfo jwtUserInfo = JwtUserInfo.builder()
                    .id((Long) authentication.getPrincipal())
                    .role((E_user_role) authentication.getCredentials())
                    .build();

            CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUserId(jwtUserInfo.id());

            if (userDetails.getRole() != jwtUserInfo.role()) {
                throw new AuthenticationException("Invalid Role") {};
            }

            return new JwtAuthenticationToken(userDetails.getAuthorities(), jwtUserInfo.id(), jwtUserInfo.role());
        }

        throw new AuthenticationException("Invalid Authentication") {};
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtAuthenticationToken.class);
    }
}
