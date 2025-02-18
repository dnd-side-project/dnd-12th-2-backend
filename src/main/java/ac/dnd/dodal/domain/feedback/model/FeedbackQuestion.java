package ac.dnd.dodal.domain.feedback.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Column;
import jakarta.persistence.IdClass;

import ac.dnd.dodal.domain.feedback.enums.FeedbackType;

@IdClass(FeedbackQuestionId.class)
@Entity(name = "feedback_questions")
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackQuestion {

    @Id
    private String question;

    @Id
    @Enumerated(EnumType.STRING)
    private FeedbackType type;

    @Column(nullable = false)
    private int orderNumber;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String description;
}
