package ac.dnd.dodal.application.feedback.service;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import ac.dnd.dodal.domain.feedback.model.DefaultFeedbackIndicator;
import ac.dnd.dodal.domain.feedback.enums.FeedbackType;
import ac.dnd.dodal.application.feedback.repository.DefaultFeedbackIndicatorRepository;

@Service
@RequiredArgsConstructor
public class DefaultFeedbackIndicatorService {

    private final DefaultFeedbackIndicatorRepository defaultFeedbackIndicatorRepository;

    public List<DefaultFeedbackIndicator> findAll() {
        return defaultFeedbackIndicatorRepository.findAll();
    }

    public List<DefaultFeedbackIndicator> findAllByQuestionAndType(
        String question, FeedbackType type) {
        return defaultFeedbackIndicatorRepository.findAllByQuestionAndType(question, type);
    }
}
