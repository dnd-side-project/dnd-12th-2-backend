package ac.dnd.dodal.application.user.usecase;

import ac.dnd.dodal.ui.user.response.GetUserAnswerResponseDto;

public interface CheckIsDoneUserAnswerUseCase {
    GetUserAnswerResponseDto checkIsDoneUserAnswer(Long userId);
}
