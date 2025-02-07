package ac.dnd.dodal.core.config.security.service;

import ac.dnd.dodal.application.user.repository.UserRepository;
import ac.dnd.dodal.core.config.security.info.CustomUserDetails;
import ac.dnd.dodal.domain.user.enums.E_user_code;
import ac.dnd.dodal.domain.user.exception.UserException;
import ac.dnd.dodal.domain.user.model.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(E_user_code.NOT_FOUND_USER));
        return CustomUserDetails.create(user);
    }

    public UserDetails loadUserByUserId(Long userId) {
        Users user = userRepository.findByIdAndRefreshTokenNotNull(userId)
                .orElseThrow(() -> new UserException(E_user_code.NOT_FOUND_USER));
        return CustomUserDetails.create(user);
    }
}
