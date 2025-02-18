package ac.dnd.dodal.domain.feedback.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.IdClass;

import ac.dnd.dodal.domain.feedback.enums.FeedbackType;

@IdClass(DefaultFeedbackIndicatorId.class)
@Entity(name = "default_feedback_indicators")
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DefaultFeedbackIndicator {

    @Id
    private String question;

    @Id
    @Enumerated(EnumType.STRING)
    private FeedbackType type;

    @Id
    @Column(nullable = false)
    private String indicator;

    @Column(nullable = false, unique = true)
    private String code;
}
