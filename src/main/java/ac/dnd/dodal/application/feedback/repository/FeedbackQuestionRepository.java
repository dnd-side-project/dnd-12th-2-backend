package ac.dnd.dodal.application.feedback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ac.dnd.dodal.domain.feedback.model.FeedbackQuestion;
import ac.dnd.dodal.domain.feedback.model.FeedbackQuestionId;
import ac.dnd.dodal.domain.feedback.enums.FeedbackType;

@Repository
public interface FeedbackQuestionRepository extends JpaRepository<FeedbackQuestion, FeedbackQuestionId> {

    @Query("SELECT fq FROM feedback_questions fq WHERE fq.type = :type ORDER BY fq.orderNumber")
    List<FeedbackQuestion> findAllByType(FeedbackType type);
}
