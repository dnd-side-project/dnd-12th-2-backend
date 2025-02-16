package ac.dnd.dodal.application.plan.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ac.dnd.dodal.domain.plan.model.Plan;
import ac.dnd.dodal.ui.plan.response.PlanElement;
// import ac.dnd.dodal.ui.plan.response.PlanWithHistoryElement;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

    @Query("SELECT p FROM plans p WHERE p.history.historyId = :historyId "
            + "ORDER BY p.endDate DESC LIMIT 1")
    Optional<Plan> findLatestPlanByHistoryId(@Param("historyId") Long historyId);

    @Query("SELECT p FROM plans p WHERE p.history.historyId = :historyId "
            + "AND p.deletedAt IS NULL AND p.completedDate IS NOT NULL "
            + "ORDER BY p.completedDate DESC")
    Page<PlanElement> findAllByHistoryId(@Param("historyId") Long historyId, Pageable pageable);

    @Query("SELECT p FROM plans p WHERE p.goal.goalId = :goalId "
            + "AND p.deletedAt IS NULL "
            + "AND p.startDate <= :date "
            + "AND p.endDate >= :date "
            + "AND p.goal.deletedAt IS NULL")
    List<PlanElement> findAllByGoalIdAndDate(
        @Param("goalId") Long goalId, @Param("date") LocalDateTime date);

//     @Query("SELECT p1, MAX(p2), p1.history.historyId FROM plans p1 "
//             + "LEFT JOIN plans p2 ON p1.history.historyId = p2.history.historyId "
//             + "WHERE p1.goal.goalId = :goalId "
//             + "AND p1.deletedAt IS NULL AND p2.deletedAt IS NULL "
//             + "AND p1.startDate <= :date "
//             + "AND p1.endDate >= :date "
//             + "AND p1.startDate > p2.startDate"
//             + "GROUP BY p1.history.historyId ")
//     List<PlanElement> findAllWithPreviousHistoryByGoalIdAndDate(
//                     @Param("goalId") Long goalId, @Param("date") LocalDateTime date);

//     @Query("SELECT new ac.dnd.dodal.ui.plan.response.PlanWithHistoryElement(p1.history.historyId, p2) FROM plans p1 "
//             + "LEFT JOIN (SELECT p2.history.historyId, MAX(p2.startDate) AS latestStartDate "
//             + "            FROM plans p2 "
//             + "            WHERE p2.deletedAt IS NULL "
//             + "            AND p2.startDate <= p1.startDate "
//             + "            GROUP BY p2.history.historyId) AS latestP2 "
//             + "ON p1.history.historyId = latestP2.historyId "
//             + "LEFT JOIN plans p2 ON p2.history.historyId = latestP2.historyId "
//             + "WHERE p1.goal.goalId = :goalId "
//             + "AND p1.deletedAt IS NULL "
//             + "AND p1.startDate <= :date "
//             + "AND p1.endDate >= :date")
//     List<PlanWithHistoryElement> findAllWithPreviousHistoryByGoalIdAndDate(
//         @Param("goalId") Long goalId, @Param("date") LocalDateTime date);

    @Query("SELECT COUNT(history) > 0 FROM plan_histories history "
            + "WHERE history.historyId = :historyId "
            + "AND history.deletedAt IS NULL "
            + "AND history.goal.goalId = :goalId "
            + "AND history.goal.deletedAt IS NULL "
            + "AND history.goal.userId = :userId")
    boolean isExistByUserIdAndGoalIdAndHistoryId(
        @Param("userId") Long userId,
        @Param("goalId") Long goalId,
        @Param("historyId") Long historyId);

    @Query("SELECT COUNT(goal) > 0 FROM goals goal "
            + "WHERE goal.goalId = :goalId "
            + "AND goal.deletedAt IS NULL "
            + "AND goal.userId = :userId")
    boolean isExistByUserIdAndGoalId(@Param("userId") Long userId, @Param("goalId") Long goalId);
}
