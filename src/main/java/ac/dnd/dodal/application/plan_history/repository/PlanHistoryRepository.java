package ac.dnd.dodal.application.plan_history.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ac.dnd.dodal.domain.plan_history.model.PlanHistory;

@Repository
public interface PlanHistoryRepository extends JpaRepository<PlanHistory, Long> {
    
}
