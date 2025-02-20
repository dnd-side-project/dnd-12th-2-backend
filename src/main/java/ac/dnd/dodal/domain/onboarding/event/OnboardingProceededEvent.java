package ac.dnd.dodal.domain.onboarding.event;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ac.dnd.dodal.domain.user.model.UserAnswer;

@Getter
@AllArgsConstructor
public class OnboardingProceededEvent {
    
    private Long userId;
    private List<UserAnswer> userAnswers;
}
