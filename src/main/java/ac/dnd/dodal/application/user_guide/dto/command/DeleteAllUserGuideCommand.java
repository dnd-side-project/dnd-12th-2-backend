package ac.dnd.dodal.application.user_guide.dto.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteAllUserGuideCommand {
    private Long userId;
}
