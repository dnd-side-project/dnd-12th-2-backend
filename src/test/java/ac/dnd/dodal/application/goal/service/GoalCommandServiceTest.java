package ac.dnd.dodal.application.goal.service;

import static org.mockito.Mockito.when;
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

import org.springframework.stereotype.Service;

import ac.dnd.dodal.common.exception.BadRequestException;
import ac.dnd.dodal.common.exception.ForbiddenException;
import ac.dnd.dodal.common.exception.UnauthorizedException;
import ac.dnd.dodal.domain.goal.GoalFixture;
import ac.dnd.dodal.domain.goal.exception.GoalExceptionCode;
import ac.dnd.dodal.domain.goal.model.Goal;
import ac.dnd.dodal.application.goal.dto.command.*;
import ac.dnd.dodal.application.goal.repository.GoalCommandRepository;
import ac.dnd.dodal.application.goal.dto.GoalCommandFixture;

@Service
@ExtendWith(MockitoExtension.class)
public class GoalCommandServiceTest {

    @Mock
    GoalCommandRepository goalCommandRepository;

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
    }

    @Test
    @DisplayName("Create a goal success")
    void create_goal_success() {
        // given
        CreateGoalCommand command = GoalCommandFixture.createGoalCommand(userId, title);
        Goal goalToSave = CreateGoalCommand.toEntity(command);
        when(goalCommandRepository.save(goalToSave)).thenReturn(goal);

        // when
        goalCommandService.create(command);

        // then
        Goal savedGoal = verify(goalCommandRepository).save(goalToSave);
        assertThat(savedGoal.getGoalId()).isEqualTo(goalId);
        assertThat(savedGoal.getUserId()).isEqualTo(userId);
        assertThat(savedGoal.getTitle()).isEqualTo(title);
    }

    @Test
    @DisplayName("Create a goal with null title")
    void create_goal_with_null_title() {
        // given
        CreateGoalCommand command = GoalCommandFixture.createGoalCommandWithNullTitle();
        Goal goalToSave = CreateGoalCommand.toEntity(command);
        when(goalCommandRepository.save(goalToSave))
                .thenThrow(new BadRequestException(GoalExceptionCode.GOAL_TITLE_EMPTY));

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
        Goal goalToSave = CreateGoalCommand.toEntity(command);
        when(goalCommandRepository.save(goalToSave))
        .thenThrow(new BadRequestException(GoalExceptionCode.GOAL_TITLE_EMPTY));
        
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
        Goal goalToSave = CreateGoalCommand.toEntity(command);
        when(goalCommandRepository.save(goalToSave))
                .thenThrow(new BadRequestException(GoalExceptionCode.GOAL_TITLE_EMPTY));

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
        Goal goalToSave = CreateGoalCommand.toEntity(command);
        when(goalCommandRepository.save(goalToSave))
                .thenThrow(new BadRequestException(GoalExceptionCode.GOAL_TITLE_EXCEED_MAX_LENGTH));

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
        when(goalCommandRepository.save(achievedGoal))
                .thenReturn(achievedGoal);

        // when
        goalCommandService.achieve(command);

        // then
        Goal savedGoal = verify(goalCommandRepository).save(achievedGoal);
        assertThat(savedGoal.getGoalId()).isEqualTo(goalId);
        assertThat(savedGoal.getUserId()).isEqualTo(userId);
        assertThat(savedGoal.getTitle()).isEqualTo(title);
        assertThat(savedGoal.getIsAchieved()).isTrue();
    }

    @Test
    @DisplayName("Achieve a goal with deleted goal")
    void achieve_goal_with_deleted_goal() {
        // given
        AchieveGoalCommand command = GoalCommandFixture
                .achieveGoalCommand(deletedGoal.getUserId(), deletedGoal.getGoalId());
        when(goalCommandRepository.save(deletedGoal))
                .thenThrow(new ForbiddenException(GoalExceptionCode.DELETED_GOAL));

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
        when(goalCommandRepository.save(achievedGoal))
                .thenThrow(new UnauthorizedException());

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
        when(goalCommandRepository.save(achievedGoal))
                .thenThrow(new BadRequestException(GoalExceptionCode.GOAL_ALREADY_ACHIEVED));

        // when & then
        assertThatThrownBy(() -> goalCommandService.achieve(command))
                .isInstanceOf(BadRequestException.class)
                .hasMessage(GoalExceptionCode.GOAL_ALREADY_ACHIEVED.getMessage());
    }

    @Test
    @DisplayName("Delete a goal success")
    void delete_goal_success() {
        // given
        DeleteGoalCommand command = GoalCommandFixture.deleteGoalCommand(userId, goalId);
        when(goalCommandRepository.save(deletedGoal)).thenReturn(deletedGoal);

        // when
        goalCommandService.delete(command);

        // then
        Goal savedGoal = verify(goalCommandRepository).save(deletedGoal);
        assertThat(savedGoal.getGoalId()).isEqualTo(goalId);
        assertThat(savedGoal.getUserId()).isEqualTo(userId);
        assertThat(savedGoal.getTitle()).isEqualTo(title);
        assertThat(savedGoal.getDeletedAt()).isNotNull();
    }

    @Test
    @DisplayName("Delete a goal with unauthorized user")
    void delete_goal_with_unauthorized_user() {
        // given
        DeleteGoalCommand command = GoalCommandFixture.deleteGoalCommand(userId + 1, goalId);
        when(goalCommandRepository.save(deletedGoal))
                .thenThrow(new UnauthorizedException());

        // when & then
        assertThatThrownBy(() -> goalCommandService.delete(command))
                .isInstanceOf(UnauthorizedException.class);
    }
}
