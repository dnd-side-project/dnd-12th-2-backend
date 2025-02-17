package ac.dnd.dodal.ui.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CreateUserAnswerRequestDto(

       List<UserAnswerData> data
) {
    public record UserAnswerData(
            @JsonProperty("question_id") Long questionId,
            @JsonProperty("answer_id") Long answerId
    )
    {}
}
