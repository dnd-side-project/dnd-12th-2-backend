package ac.dnd.dodal.application.feedback.service;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import ac.dnd.dodal.application.feedback.repository.FeedbackQuestionRepository;
import ac.dnd.dodal.domain.feedback.model.FeedbackQuestion;
import ac.dnd.dodal.domain.feedback.enums.FeedbackType;

@Service
@RequiredArgsConstructor
public class FeedbackQuestionService {

    private final FeedbackQuestionRepository feedbackQuestionRepository;

    public List<FeedbackQuestion> findAll() {
        return feedbackQuestionRepository.findAll();
    }

    public List<FeedbackQuestion> findAllByType(FeedbackType type) {
        return feedbackQuestionRepository.findAllByType(type);
    }
}
