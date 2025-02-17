package ac.dnd.dodal.application.onboarding.repository;

import ac.dnd.dodal.domain.onboarding.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query("SELECT a FROM Answer a WHERE a.question.id IN :questionIds AND a.deletedAt IS NULL")
    List<Answer> findAllByQuestionId(
            @Param("questionIds") List<Long> questionIds);

    @Query("SELECT a FROM Answer a WHERE a.question.id = :questionId AND a.answerId = :answerId AND a.deletedAt IS NULL")
    Optional<Answer> findByQuestionIdAndAnswerId(Long questionId, Long answerId);
}
