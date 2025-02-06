package ac.dnd.dodal.application.user.repository;

import ac.dnd.dodal.domain.user.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    @Query("UPDATE Users u SET u.refreshToken = :refreshToken WHERE u.id = :id")
    void updateRefreshToken(Long id, String refreshToken);

    @Query("SELECT u FROM Users u WHERE u.email = :email")
    Optional<Users> findByEmail(String email);

    @Query("SELECT u FROM Users u WHERE u.id = :id AND u.refreshToken IS NOT NULL")
    Optional<Users> findByIdAndRefreshTokenNotNull(Long id);

}
