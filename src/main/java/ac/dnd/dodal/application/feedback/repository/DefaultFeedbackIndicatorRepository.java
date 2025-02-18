package ac.dnd.dodal.application.feedback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ac.dnd.dodal.domain.feedback.model.DefaultFeedbackIndicator;
import ac.dnd.dodal.domain.feedback.model.DefaultFeedbackIndicatorId;
import ac.dnd.dodal.domain.feedback.enums.FeedbackType;

@Repository
public interface DefaultFeedbackIndicatorRepository
        extends JpaRepository<DefaultFeedbackIndicator, DefaultFeedbackIndicatorId> {

    @Query("SELECT df FROM default_feedback_indicators df WHERE df.question = :question AND df.type = :type")
    List<DefaultFeedbackIndicator> findAllByQuestionAndType(String question, FeedbackType type);
}
