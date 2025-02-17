package ac.dnd.dodal.ui.onboarding.response;

import ac.dnd.dodal.domain.onboarding.model.Answer;

public record AnswerResponseDto(
        Long answerId,
        String content
) {

    public static AnswerResponseDto of(Answer answer) {
        return new AnswerResponseDto(answer.getAnswerId(), answer.getContent().getContent());
    }
}