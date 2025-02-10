package ac.dnd.dodal.application.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import ac.dnd.dodal.application.user.dto.UserCommandFixture;
import ac.dnd.dodal.application.user.repository.UserRepository;
import ac.dnd.dodal.domain.user.enums.UserRole;
import ac.dnd.dodal.domain.user.model.User;
import ac.dnd.dodal.ui.user.request.OAuthUserInfoRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserCommandServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserCommandService userCommandService;

    private OAuthUserInfoRequestDto authSignUpRequestDto;
    private User mockUser;

    @BeforeEach
    void setUp() {
        authSignUpRequestDto = UserCommandFixture.signUpUser();

        // Mock된 Users 객체 생성
        mockUser = new User("testUser", "testUserProfileImageUrl", "testDeviceToken", "testEmail@test.example", UserRole.USER);

        // ✅ save() 호출 시 mockUser를 반환하도록 설정
        when(userRepository.save(any(User.class))).thenReturn(mockUser);
    }

    @Test
    @DisplayName("카카오 소셜 회원가입 성공")
    void createUserBySocialSignUp() {
        // given
        OAuthUserInfoRequestDto testDto = authSignUpRequestDto;

        // when
        User user = userCommandService.createUserBySocialSignUp(authSignUpRequestDto);

        // then
        verify(userRepository, times(1)).save(any(User.class));  // userRepository.save()가 1번 호출되었는지 확인
        assertThat(user.getEmail()).isEqualTo(testDto.email());
        assertThat(user.getNickname()).isEqualTo(testDto.nickname());
    }
}