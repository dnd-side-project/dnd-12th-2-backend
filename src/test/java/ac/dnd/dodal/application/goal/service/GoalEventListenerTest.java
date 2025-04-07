package ac.dnd.dodal.application.goal.service;

import static org.assertj.core.api.Assertions.assertThat;

import ac.dnd.dodal.application.goal.repository.GoalRepository;
import ac.dnd.dodal.application.user.dto.UserCommandFixture;
import ac.dnd.dodal.application.user.service.UserCommandService;
import ac.dnd.dodal.domain.goal.model.Goal;
import ac.dnd.dodal.domain.user.event.UserWithdrawnEvent;
import ac.dnd.dodal.domain.user.model.User;
import ac.dnd.dodal.ui.auth.request.OAuthUserInfoRequestDto;
import jakarta.transaction.Transactional;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class GoalEventListenerTest {

    @Autowired
    private ApplicationEventPublisher eventPublisher;
    
    @Autowired
    private UserCommandService userCommandService;

    @Autowired
    private GoalRepository goalRepository;

    @Test
    @DisplayName("사용자가 탈퇴 시 사용자와 관계된 모든 목표들이 삭제된다.")
    void delete_user_all_goals_when_user_withdrawn() {
        //given
        OAuthUserInfoRequestDto authSignUpRequestDto = UserCommandFixture.signUpUser();
        User user = userCommandService.createUserBySocialSignUp(authSignUpRequestDto);

        List<Goal> goals = List.of(
                new Goal(user.getId(), "goal1"),
                new Goal(user.getId(), "goal2")
        );
        goalRepository.saveAll(goals);
        assertThat(goals.getLast().getDeletedAt()).isNull();
        eventPublisher.publishEvent(new UserWithdrawnEvent(user.getId()));

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        //when&then
        assertThat(goals).hasSize(2);
        assertThat(goals.getFirst().getDeletedAt()).isNotNull();
        assertThat(goals.getLast().getDeletedAt()).isNotNull();
    }
}
