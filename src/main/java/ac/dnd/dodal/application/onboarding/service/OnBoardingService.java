package ac.dnd.dodal.application.onboarding.service;

import ac.dnd.dodal.application.onboarding.repository.AnswerRepository;
import ac.dnd.dodal.application.onboarding.repository.QuestionRepository;
import ac.dnd.dodal.application.onboarding.usecase.GetQuestionsAndAnswersUseCase;
import ac.dnd.dodal.domain.onboarding.model.Answer;
import ac.dnd.dodal.domain.onboarding.model.Question;
import ac.dnd.dodal.ui.onboarding.response.GetOnBoardingResponseDto;
import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OnBoardingService implements GetQuestionsAndAnswersUseCase {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Override
    public List<GetOnBoardingResponseDto> getQuestionsAndAnswers() {
        List<Question> questions = getQuestions();
        Map<Long, List<Answer>> answers = getAnswers(questions);
        return mapQuestionAndAnswerToResponseDto(questions, answers);
    }

    private List<Question> getQuestions(){
        return questionRepository.findAllByOrderAsc();
    }

    private Map<Long, List<Answer>> getAnswers(List<Question> questions){
        return answerRepository.findAllByQuestionId(questions.stream().map(Question::getId).collect(Collectors.toList())).stream()
                .collect(Collectors.groupingBy(answer -> answer.getQuestion().getId()));
    }

    private List<GetOnBoardingResponseDto> mapQuestionAndAnswerToResponseDto(List<Question> questions, Map<Long, List<Answer>> answers){
        return questions.stream()
                .map(question -> GetOnBoardingResponseDto.of(
                        question,
                        answers.getOrDefault(question.getId(), Collections.emptyList())
                ))
                .collect(Collectors.toList());
    }

}