package ac.dnd.dodal.application.onboarding.repository;

import ac.dnd.dodal.domain.onboarding.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT q FROM Question q ORDER BY q.order ASC")
    List<Question> findAllByOrderAsc();
}