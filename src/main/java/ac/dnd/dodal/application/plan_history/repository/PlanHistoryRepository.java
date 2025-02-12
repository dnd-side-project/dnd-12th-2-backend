package ac.dnd.dodal.application.plan_history.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ac.dnd.dodal.domain.plan_history.model.PlanHistory;

public interface PlanHistoryRepository extends JpaRepository<PlanHistory, Long> {
    
}
