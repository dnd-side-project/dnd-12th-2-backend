package ac.dnd.dodal.application.onboarding.repository;

import ac.dnd.dodal.domain.onboarding.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query("SELECT a FROM Answer a WHERE a.question.id IN :questionIds")
    List<Answer> findAllByQuestionId(
            @Param("questionIds") List<Long> questionIds);
}