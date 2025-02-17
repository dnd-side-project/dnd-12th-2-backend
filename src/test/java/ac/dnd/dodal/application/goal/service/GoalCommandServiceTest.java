package ac.dnd.dodal.application.goal.service;

import static org.mockito.Mockito.when;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.context.ApplicationEventPublisher;

import ac.dnd.dodal.common.exception.BadRequestException;
import ac.dnd.dodal.common.exception.ForbiddenException;
import ac.dnd.dodal.common.exception.UnauthorizedException;
import ac.dnd.dodal.domain.goal.GoalFixture;
import ac.dnd.dodal.domain.goal.event.GoalCreatedEvent;
import ac.dnd.dodal.domain.goal.exception.GoalExceptionCode;
import ac.dnd.dodal.domain.goal.model.Goal;
import ac.dnd.dodal.application.goal.dto.command.*;
import ac.dnd.dodal.application.goal.dto.GoalCommandFixture;

@ExtendWith(MockitoExtension.class)
public class GoalCommandServiceTest {

    @Mock
    GoalService goalService;

    @Mock
    ApplicationEventPublisher eventPublisher;

    @InjectMocks
    GoalCommandService goalCommandService;

    Long goalId;
    Long userId;
    String title;
    Goal goal;
    Goal achievedGoal;
    Goal deletedGoal;

    @BeforeEach
    void setUp() {
        goal = GoalFixture.goal();
        achievedGoal = GoalFixture.achievedGoal();
        deletedGoal = GoalFixture.deletedGoal();

        goalId = goal.getGoalId();
        userId = goal.getUserId();
        title = goal.getTitle();

        lenient().when(goalService.findByIdOrThrow(goalId))
            .thenReturn(goal);
    }

    @Test
    @DisplayName("Create a goal success")
    void create_goal_success() {
        // given
        CreateGoalCommand command = GoalCommandFixture.createGoalCommand(userId, title);
        when(goalService.saveAndFlush(any(Goal.class))).thenAnswer(invocation -> {
            Goal goal = invocation.getArgument(0);
            ReflectionTestUtils.setField(goal, "goalId", goalId);
            return goal;
        });

        // when
        Long savedGoalId = goalCommandService.create(command);

        // then
        verify(goalService).saveAndFlush(any(Goal.class));
        verify(eventPublisher).publishEvent(new GoalCreatedEvent(goalId));
        assertThat(savedGoalId).isEqualTo(goalId);
    }

    @Test
    @DisplayName("Create a goal with null title")
    void create_goal_with_null_title() {
        // given
        CreateGoalCommand command = GoalCommandFixture.createGoalCommandWithNullTitle();

        // when & then
        assertThatThrownBy(() -> goalCommandService.create(command))
                .isInstanceOf(BadRequestException.class)
                .hasMessage(GoalExceptionCode.GOAL_TITLE_EMPTY.getMessage());
    }
    
    @Test
    @DisplayName("Create a goal with empty title")
    void create_goal_with_empty_title() {
        // given
        CreateGoalCommand command = GoalCommandFixture.createGoalCommandWithEmptyTitle();

        // when & then
        assertThatThrownBy(() -> goalCommandService.create(command))
                .isInstanceOf(BadRequestException.class)
                .hasMessage(GoalExceptionCode.GOAL_TITLE_EMPTY.getMessage());
    }

    @Test
    @DisplayName("Create a goal with blank title")
    void create_goal_with_blank_title() {
        // given
        CreateGoalCommand command = GoalCommandFixture.createGoalCommandWithBlankTitle();

        // when & then
        assertThatThrownBy(() -> goalCommandService.create(command))
                .isInstanceOf(BadRequestException.class)
                .hasMessage(GoalExceptionCode.GOAL_TITLE_EMPTY.getMessage());
    }

    @Test
    @DisplayName("Create a goal with exceed title length")
    void create_goal_with_exceed_title_length() {
        // given
        CreateGoalCommand command = GoalCommandFixture.createGoalCommandExceedTitleLength();

        // when & then
        assertThatThrownBy(() -> goalCommandService.create(command))
                .isInstanceOf(BadRequestException.class)
                .hasMessage(GoalExceptionCode.GOAL_TITLE_EXCEED_MAX_LENGTH.getMessage());
    }

    @Test
    @DisplayName("Achieve a goal success")
    void achieve_goal_success() {
        // given
        AchieveGoalCommand command = GoalCommandFixture.achieveGoalCommand(userId, goalId);

        // when
        goalCommandService.achieve(command);

        // then
        verify(goalService).save(argThat(goal -> goal.getIsAchieved() == true));
    }

    @Test
    @DisplayName("Achieve a goal with deleted goal")
    void achieve_goal_with_deleted_goal() {
        // given
        AchieveGoalCommand command = GoalCommandFixture
                .achieveGoalCommand(deletedGoal.getUserId(), deletedGoal.getGoalId());
        when(goalService.findByIdOrThrow(goalId))
            .thenReturn(deletedGoal);

        // when & then
        assertThatThrownBy(() -> goalCommandService.achieve(command))
                .isInstanceOf(ForbiddenException.class)
                .hasMessage(GoalExceptionCode.DELETED_GOAL.getMessage());
    }

    @Test
    @DisplayName("Achieve a goal with unauthorized user")
    void achieve_goal_with_unauthorized_user() {
        // given
        AchieveGoalCommand command = GoalCommandFixture
                .achieveGoalCommand(userId + 1, goalId);

        // when & then
        assertThatThrownBy(() -> goalCommandService.achieve(command))
                .isInstanceOf(UnauthorizedException.class);
    }

    @Test
    @DisplayName("Achieve a goal with already achieved goal")
    void achieve_goal_with_already_achieved_goal() {
        // given
        AchieveGoalCommand command = GoalCommandFixture.achieveGoalCommand(achievedGoal.getUserId(),
                achievedGoal.getGoalId());
        when(goalService.findByIdOrThrow(goalId))
                .thenReturn(achievedGoal);

        // when & then
        assertThatThrownBy(() -> goalCommandService.achieve(command))
                .isInstanceOf(ForbiddenException.class)
                .hasMessage(GoalExceptionCode.GOAL_ALREADY_ACHIEVED.getMessage());
    }

    @Test
    @DisplayName("Delete a goal success")
    void delete_goal_success() {
        // given
        DeleteGoalCommand command = GoalCommandFixture.deleteGoalCommand(userId, goalId);

        // when
        goalCommandService.delete(command);

        // then
        verify(goalService).save(argThat(goal -> goal.getDeletedAt() != null));
    }

    @Test
    @DisplayName("Delete a goal with unauthorized user")
    void delete_goal_with_unauthorized_user() {
        // given
        DeleteGoalCommand command = GoalCommandFixture.deleteGoalCommand(userId + 1, goalId);

        // when & then
        assertThatThrownBy(() -> goalCommandService.delete(command))
                .isInstanceOf(UnauthorizedException.class);
    }
    
    @Test
    @DisplayName("Delete a goal which is already deleted")
    void delete_goal_already_deleted() {
        // given
        DeleteGoalCommand command = GoalCommandFixture.deleteGoalCommand(userId, goalId);
        when(goalService.findByIdOrThrow(goalId))
            .thenReturn(deletedGoal);

        // when & then
        assertThatThrownBy(() -> goalCommandService.delete(command))
                .isInstanceOf(ForbiddenException.class)
                .hasMessage(GoalExceptionCode.GOAL_ALREADY_DELETED.getMessage());
    }
}
