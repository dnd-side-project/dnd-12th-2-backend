package ac.dnd.dodal.application.user.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;

import ac.dnd.dodal.application.user.dto.UserQueryFixture;
import ac.dnd.dodal.application.user.repository.UserRepository;
import ac.dnd.dodal.domain.user.model.User;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserQueryServiceTest {
    @Mock
    UserRepository userRepository;

    String email;
    String nickname;

    @BeforeEach
    void setUp() {
        email = "test@test.example.com";
        nickname = "testUser";
        User saveUser = UserQueryFixture.saveUser();

        lenient().when(userRepository.findByEmail(email))
                .thenReturn(Optional.of(saveUser));

        lenient().when(userRepository.existsByEmail(email))
                .thenReturn(true);
        lenient().when(userRepository.existsByNickname(nickname))
                .thenReturn(true);

        // 새로운 이메일과 닉네임에 대해 false 반환하도록 Mocking
        lenient().when(userRepository.existsByEmail("test2@test2.com"))
                .thenReturn(false);
        lenient().when(userRepository.existsByNickname("testUser2"))
                .thenReturn(false);
    }

    @Test
    @DisplayName("이메일과 권한으로 사용자 조회 성공")
    void userSocialLoginKakao() {
        // given
        User user = UserQueryFixture.saveUser();
        // when
        lenient().when(userRepository.findByEmail(email))
                .thenReturn(Optional.of(user));

        lenient().when(userRepository.existsByEmail(email))
                .thenReturn(true);

        lenient().when(userRepository.existsByNickname(nickname))
                .thenReturn(true);

        // then
    }

    @Test
    @DisplayName("이메일 중복 체크")
    void userCheckDuplicatedEmail(){
// given
        String duplicatedEmail = email;
        String newEmail = "test2@test2.com";

        // when
        boolean isDuplicated = userRepository.existsByEmail(duplicatedEmail);
        boolean isNotDuplicated = userRepository.existsByEmail(newEmail);

        // then
        assertThat(isDuplicated).isTrue();
        assertThat(isNotDuplicated).isFalse();
    }

    @Test
    @DisplayName("닉네임 중복 체크")
    void userCheckDuplicatedNickname(){
        // given
        String duplicatedNickname = nickname;
        String newNickname = "testUser2";
        // when
        boolean isDuplicated = userRepository.existsByNickname(duplicatedNickname);

        // then
        assertThat(isDuplicated).isTrue();

        //when
        boolean isNotDuplicated = userRepository.existsByNickname(newNickname);

        //then
        assertThat(isNotDuplicated).isFalse();
    }
}
