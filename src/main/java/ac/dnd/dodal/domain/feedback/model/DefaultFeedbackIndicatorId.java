package ac.dnd.dodal.domain.feedback.model;

import ac.dnd.dodal.domain.feedback.enums.FeedbackType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DefaultFeedbackIndicatorId {

    private String question;
    private FeedbackType type;
    private String indicator;
}
