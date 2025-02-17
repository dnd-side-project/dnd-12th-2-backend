package ac.dnd.dodal.application.plan_history.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import ac.dnd.dodal.domain.plan_history.model.HistoryStatistics;

@Repository
public interface HistoryStatisticsRepository extends JpaRepository<HistoryStatistics, Long> {
    
}
