package ac.dnd.dodal.ui.onboarding.response;

import ac.dnd.dodal.domain.onboarding.enums.AnswerContent;
import ac.dnd.dodal.domain.onboarding.model.Answer;
import ac.dnd.dodal.domain.onboarding.model.Question;

import java.util.List;

public record GetOnBoardingResponseDto(
        String question,
        String subContent,
        int order,
        List<String> answers
) {
    public static GetOnBoardingResponseDto of(Question question, List<Answer> answers) {
        return new GetOnBoardingResponseDto(
                question.getQuestionContent().getMainContent(),
                question.getQuestionContent().getSubContent(),
                question.getOrder(),
                answers.stream().map(Answer::getContent).map(AnswerContent::getContent).toList()
        );
    }
}