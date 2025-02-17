package ac.dnd.dodal.ui.onboarding.response;

import ac.dnd.dodal.domain.onboarding.model.Answer;
import ac.dnd.dodal.domain.onboarding.model.Question;
import java.util.List;

public record GetOnBoardingResponseDto(
        Long questionId,
        String questionContent,
        String subContent,
        int order,
        List<AnswerResponseDto> answers
) {

    public static GetOnBoardingResponseDto of(Question question, List<Answer> answers) {
        return new GetOnBoardingResponseDto(
                question.getId(),
                question.getQuestionContent().getMainContent(),
                question.getQuestionContent().getSubContent(),
                question.getOrder(),
                answers.stream().map(AnswerResponseDto::of).toList());
    }
}