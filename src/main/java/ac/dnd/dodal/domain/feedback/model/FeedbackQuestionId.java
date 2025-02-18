package ac.dnd.dodal.domain.feedback.model;

import java.io.Serializable;

import ac.dnd.dodal.domain.feedback.enums.FeedbackType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FeedbackQuestionId implements Serializable {

    private String question;
    private FeedbackType type;
}
