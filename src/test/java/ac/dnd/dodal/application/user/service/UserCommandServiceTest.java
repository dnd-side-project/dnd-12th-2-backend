package ac.dnd.dodal.application.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import ac.dnd.dodal.application.user.dto.UserCommandFixture;
import ac.dnd.dodal.application.user.repository.UserAnswerRepository;
import ac.dnd.dodal.common.util.OAuth2Util;
import ac.dnd.dodal.domain.onboarding.enums.AnswerContent;
import ac.dnd.dodal.domain.user.enums.UserRole;
import ac.dnd.dodal.domain.user.model.User;
import ac.dnd.dodal.domain.user.model.UserAnswer;
import ac.dnd.dodal.ui.auth.request.OAuthUserInfoRequestDto;
import ac.dnd.dodal.ui.user.request.WithdrawUserRequestDto;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class UserCommandServiceTest {

    @Autowired
    private UserAnswerRepository userAnswerRepository;

    @Autowired
    private UserCommandService userCommandService;

    @MockBean
    private OAuth2Util oAuth2Util;

    private OAuthUserInfoRequestDto authSignUpRequestDto;
    private User mockUser;

    @BeforeEach
    void setUp() {
        authSignUpRequestDto = UserCommandFixture.signUpUser();
        mockUser = new User("testUser", "testUserProfileImageUrl", "testDeviceToken", "testEmail@test.example", UserRole.USER);
    }

    @Test
    @DisplayName("카카오 소셜 회원가입 성공")
    void createUserBySocialSignUp() {
        // given
        OAuthUserInfoRequestDto testDto = authSignUpRequestDto;

        // when
        User user = userCommandService.createUserBySocialSignUp(authSignUpRequestDto);

        // then
        assertThat(user.getCreatedAt()).isNotNull();
        assertThat(user.getEmail()).isEqualTo(testDto.email());
        assertThat(user.getNickname()).isEqualTo(testDto.nickname());
        assertThat(user.getDeletedAt()).isNull();
    }

    @Test
    @DisplayName("회원 탈퇴 시 회원 정보와 온보딩 시 응답한 답변들이 삭제된다.")
    void deleteUserInfoWhenUserWithdraw() {
        // given
        User user = userCommandService.createUserBySocialSignUp(authSignUpRequestDto);

        List<UserAnswer> userAnswers = List.of(
            new UserAnswer("현재 가장 관심 있는 목표 분야를 선택해 주세요.", AnswerContent.INTEREST_GOAL_1, user, 1),
            new UserAnswer("계획을 세울 때 선호하는 방식을 골라주세요.", AnswerContent.PREFERENCE_SET_PLAN_1, user, 1),
            new UserAnswer("그동안 계획 설정 시 어려웠던 점을 선택해 주세요.", AnswerContent.DIFFICULTY_SET_PLAN_2, user, 1)
        );

        WithdrawUserRequestDto withdrawRequest = new WithdrawUserRequestDto(
            "test_device_token",
            "test_authorization_code"
        );

        List<UserAnswer> userAnswerList = userAnswerRepository.saveAll(userAnswers);

        assertThat(userAnswerList).hasSize(3);
        assertThat(userAnswerList.getFirst().getCreatedAt()).isNotNull();
        assertThat(userAnswerList.getFirst().getDeletedAt()).isNull();

        // Apple 토큰 관련 Mock 설정
        when(oAuth2Util.getAppleAccessToken(anyString())).thenReturn("test_access_token");

        // when
        userCommandService.withdrawUser(user.getId(), withdrawRequest);

        // then
        assertThat(user.getDeletedAt()).isNotNull();

        try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
        }

        List<UserAnswer> deletedUserAnswers = userAnswerRepository.findAllByUser(user);
        assertThat(deletedUserAnswers).isEmpty();
    }
}