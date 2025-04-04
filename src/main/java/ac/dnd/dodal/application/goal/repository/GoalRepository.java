package ac.dnd.dodal.application.goal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ac.dnd.dodal.domain.goal.model.Goal;
import ac.dnd.dodal.ui.goal.response.GoalStatisticsResponse;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {

    @Query("SELECT new ac.dnd.dodal.ui.goal.response.GoalStatisticsResponse("
    + "g.goalId, "
    + "g.title, "
    + "gs.successCount, "
    + "gs.failureCount, "
    + "gs.totalCount) "
    + "FROM goals g "
    + "JOIN goal_statistics gs ON gs.goal = g "
    + "WHERE g.userId = :userId "
    + "AND g.deletedAt IS NULL "
    + "AND g.isAchieved = false "
    + "ORDER BY g.updatedAt DESC")
    List<GoalStatisticsResponse> findUnAchievedGoalStatisticsResponsesByUserId(
        @Param("userId") Long userId);

    @Query("SELECT new ac.dnd.dodal.ui.goal.response.GoalStatisticsResponse("
    + "g.goalId, "
    + "g.title, "
    + "gs.successCount, "
    + "gs.failureCount, "
    + "gs.totalCount) "
    + "FROM goals g "
    + "JOIN goal_statistics gs ON gs.goal = g "
    + "WHERE g.userId = :userId "
    + "AND g.deletedAt IS NULL "
    + "AND g.isAchieved = true "
    + "ORDER BY g.updatedAt DESC")
    List<GoalStatisticsResponse> findAchievedGoalStatisticsResponsesByUserId(
        @Param("userId") Long userId);

    @Query("SELECT g FROM goals g WHERE g.userId = :userId AND g.deletedAt IS NULL")
    List<Goal> findAllByUserId(@Param("userId") Long userId);
}
