package ac.dnd.dodal.application.user.usecase;

import ac.dnd.dodal.domain.user.enums.UserRole;
import ac.dnd.dodal.domain.user.model.User;

import java.util.Optional;

public interface UserQueryUseCase {

  User findByEmailAndRole(String email, UserRole role);

  User findByEmailAndRoleIncludeWithdrawnUser(String email, UserRole role);

  Optional<User> findByEmail(String email);

  void checkDuplicatedEmail(String email);

  User findByIdAndRole(Long id, UserRole userRole);

  User findById(Long id);
}
