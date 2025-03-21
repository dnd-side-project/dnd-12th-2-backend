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
import ac.dnd.dodal.application.plan.dto.PlanModel;
import ac.dnd.dodal.domain.plan.model.Plan;
import ac.dnd.dodal.ui.plan.response.PlanElement;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

    @Query("SELECT p FROM plans p WHERE p.history.historyId = :historyId "
            + "ORDER BY p.endDate DESC LIMIT 1")
    Optional<Plan> findLatestPlanByHistoryId(@Param("historyId") Long historyId);

    @Query("SELECT p FROM plans p WHERE p.history.historyId = :historyId "
            + "AND p.deletedAt IS NULL AND p.completedDate IS NOT NULL "
            + "ORDER BY p.completedDate DESC")
    Page<PlanElement> findAllByHistoryId(@Param("historyId") Long historyId, Pageable pageable);

    @Query("SELECT new ac.dnd.dodal.application.plan.dto.PlanModel("
            + "p.planId, "
            + "p.history.historyId, "
            + "p.goal.goalId, "
            + "p.title, "
            + "p.status, "
            + "p.guide, "
            + "p.startDate, "
            + "p.endDate, "
            + "p.completedDate) "
            + "FROM plans p "
            + "WHERE p.goal.goalId = :goalId "
            + "AND p.deletedAt IS NULL "
            + "AND ((p.startDate < :endDate AND p.startDate >= :startDate) "
            + "OR (p.endDate < :endDate AND p.endDate >= :startDate)) "
            + "AND p.goal.deletedAt IS NULL "
            + "ORDER BY p.startDate ASC")
    List<PlanModel> findAllByGoalIdAndDate(
        @Param("goalId") Long goalId,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate);

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

    @Query("SELECT new ac.dnd.dodal.application.plan.dto.PlanModel("
            + "p.planId, "
            + "p.history.historyId, "
            + "p.goal.goalId, "
            + "p.title, "
            + "p.status, "
            + "p.guide, "
            + "p.startDate, "
            + "p.endDate, "
            + "p.completedDate) "
            + "FROM plans p "
            + "WHERE p.history.historyId = (SELECT p2.history.historyId FROM plans p2 "
            + "WHERE p2.planId = :planId "
            + "AND p2.goal.userId = :userId "
            + "AND p2.deletedAt IS NULL) "
            + "AND p.completedDate <= (SELECT p3.completedDate FROM plans p3 "
            + "WHERE p3.planId = :planId "
            + "AND p3.deletedAt IS NULL) "
            + "AND p.deletedAt IS NULL "
            + "ORDER BY p.completedDate DESC "
            + "LIMIT :size")
    List<PlanElement> findHistoriesByPlanId(
        @Param("planId") Long planId, @Param("userId") Long userId, @Param("size") int size);
}
