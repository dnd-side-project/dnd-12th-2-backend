package ac.dnd.dodal.application.onboarding.repository;

import ac.dnd.dodal.domain.onboarding.model.Question;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT COUNT(q) FROM Question q WHERE q.deletedAt IS NULL")
    Long countAllQuestions();

    @Query("SELECT q FROM Question q WHERE q.deletedAt IS NULL ORDER BY q.order ASC")
    List<Question> findAllByOrderAsc();
}