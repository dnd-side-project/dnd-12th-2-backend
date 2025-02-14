package ac.dnd.dodal.application.plan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
}
