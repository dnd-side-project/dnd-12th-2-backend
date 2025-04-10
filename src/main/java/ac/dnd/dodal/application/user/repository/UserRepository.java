package ac.dnd.dodal.application.user.repository;

import ac.dnd.dodal.domain.user.enums.UserRole;
import ac.dnd.dodal.domain.user.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Transactional
  @Modifying
  @Query("UPDATE User u SET u.refreshToken = :refreshToken WHERE u.id = :id")
  void updateRefreshToken(Long id, String refreshToken);

  @Query("SELECT u FROM User u WHERE u.email = :email")
  Optional<User> findByEmail(String email);

  @Query("SELECT u FROM User u WHERE u.id = :id AND u.refreshToken IS NOT NULL")
  Optional<User> findByIdAndRefreshTokenNotNull(Long id);

  @Query("SELECT u FROM User u WHERE u.email = :email AND u.role = :role AND u.deletedAt IS NULL")
  User findByEmailAndRole(String email, UserRole role);

  @Query("SELECT u FROM User u WHERE u.email = :email AND u.role = :role")
  User findByEmailAndRoleIncludeWithdrawnUser(String email, UserRole role);

  @Query(
      "SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM User u WHERE u.email = :email")
  boolean existsByEmail(String email);

  @Query(
      "SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM User u WHERE u.nickname = :nickname")
  boolean existsByNickname(String nickname);

  @Query("SELECT u FROM User u WHERE u.id = :id AND u.deletedAt IS NULL")
  Optional<User> findByUserId(Long id);
}
