package ac.dnd.dodal.application.plan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ac.dnd.dodal.domain.plan.model.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    @Query("SELECT p FROM plans p WHERE p.history.historyId = :historyId " + 
        "ORDER BY p.endDate DESC LIMIT 1")
    Optional<Plan> findLatestPlanByHistoryId(@Param("historyId") Long historyId);
}
