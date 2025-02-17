package ac.dnd.dodal.application.onboarding.usecase;

import ac.dnd.dodal.ui.onboarding.response.GetOnBoardingResponseDto;

import java.util.List;

public interface GetQuestionsAndAnswersUseCase {

    List<GetOnBoardingResponseDto> getQuestionsAndAnswers();
}