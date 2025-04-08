package ac.dnd.dodal.application.user_guide.service;

import static org.assertj.core.api.Assertions.assertThat;

import ac.dnd.dodal.IntegrationTest;
import ac.dnd.dodal.domain.guide.model.UserGuide;
import ac.dnd.dodal.domain.user.UserFixture;
import ac.dnd.dodal.domain.user.event.UserWithdrawnEvent;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class UserGuideEventListenerTest extends IntegrationTest {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private UserGuideService userGuideService;

    @Test
    @DisplayName("사용자 탈퇴 시 사용자 가이드들을 모두 삭제한다.")
    void delete_all_user_guides_when_user_deleted() {
        // given
        Long userId = UserFixture.USER_ID;
        List<UserGuide> userGuides = userGuideService.findAllByUserId(userId);
        assertThat(userGuides.getLast().getDeletedAt()).isNull();
        assertThat(userGuides).isNotEmpty();

        // when
        eventPublisher.publishEvent(new UserWithdrawnEvent(userId));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // then
        assertThat(userGuides.getLast().getDeletedAt()).isNotNull();
    }
}
