package ac.dnd.dodal.core.security.service;

import ac.dnd.dodal.application.user.repository.UserRepository;
import ac.dnd.dodal.core.security.info.CustomUserDetails;
import ac.dnd.dodal.domain.user.enums.UserExceptionCode;
import ac.dnd.dodal.domain.user.exception.UserNotFoundException;
import ac.dnd.dodal.domain.user.model.User;
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
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(UserExceptionCode.NOT_FOUND_USER));
        return CustomUserDetails.create(user);
    }

    public UserDetails loadUserByUserId(Long userId) {
        User user = userRepository.findByIdAndRefreshTokenNotNull(userId)
                .orElseThrow(() -> new UserNotFoundException(UserExceptionCode.NOT_FOUND_USER));
        return CustomUserDetails.create(user);
    }
}
