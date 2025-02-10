package ac.dnd.dodal.application.user.usecase;


import ac.dnd.dodal.domain.user.enums.UserRole;
import ac.dnd.dodal.domain.user.model.User;

public interface UserQueryUseCase {

    User findByEmailAndRole(String email, UserRole role);

    void checkDuplicatedEmail(String email);

    void checkDuplicatedNickname(String nickname);
}
