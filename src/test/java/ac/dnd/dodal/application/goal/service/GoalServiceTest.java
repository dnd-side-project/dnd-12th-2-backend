package ac.dnd.dodal.application.goal.service;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import ac.dnd.dodal.common.exception.NotFoundException;
import ac.dnd.dodal.domain.goal.model.Goal;
import ac.dnd.dodal.domain.goal.GoalFixture;
import ac.dnd.dodal.domain.goal.exception.GoalExceptionCode;
import ac.dnd.dodal.application.goal.repository.GoalRepository;

@ExtendWith(MockitoExtension.class)
public class GoalServiceTest {

    @Mock
    GoalRepository goalRepository;

    @InjectMocks
    GoalService goalService;

    Goal goal;

    @BeforeEach
    void setUp() {
        goal = GoalFixture.goal();
        lenient().when(goalRepository.save(goal)).thenReturn(goal);
        lenient().when(goalRepository.findById(goal.getGoalId())).thenReturn(Optional.of(goal));
    }

    @Test
    @DisplayName("Save goal test success")
    void save_test() {
        // when
        Goal result = goalService.save(goal);

        // then
        assertThat(result).isEqualTo(goal);
    }

    @Test
    @DisplayName("Find goal by id test success")
    void find_by_id_test() {
        // when
        Optional<Goal> result = goalService.findById(goal.getGoalId());

        // then
        assertThat(result).isEqualTo(Optional.of(goal));
    }

    @Test
    @DisplayName("Find goal by id or throw-find goal")
    void find_by_id_or_throw_find_goal_test() {
        // when
        Goal result = goalService.findByIdOrThrow(goal.getGoalId());

        // then
        assertThat(result).isEqualTo(goal);
    }

    @Test
    @DisplayName("Find goal by id or throw NotFoundException-throw exception")
    void find_by_id_or_throw_not_found_exception_test() {
        // when
        when(goalRepository.findById(goal.getGoalId())).thenReturn(Optional.empty());

        // then
        assertThatThrownBy(() -> goalService.findByIdOrThrow(goal.getGoalId()))
                .isInstanceOf(NotFoundException.class)
                .hasMessage(GoalExceptionCode.GOAL_NOT_FOUND.getMessage());
    }
}
