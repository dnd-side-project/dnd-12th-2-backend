package ac.dnd.dodal.application.goal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ac.dnd.dodal.domain.goal.model.Goal;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {

    Optional<Goal> findById(Long goalId);
}
