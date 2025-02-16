package ac.dnd.dodal.application.user.usecase;

import ac.dnd.dodal.ui.user.request.CreateUserAnswerRequestDto;

import java.util.List;

public interface CreateUserAnswerUseCase {

    void createUserAnswer(Long userId, CreateUserAnswerRequestDto createUserAnswerRequestDto);
}
