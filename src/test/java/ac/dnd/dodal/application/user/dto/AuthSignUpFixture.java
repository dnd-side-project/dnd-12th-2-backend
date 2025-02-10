package ac.dnd.dodal.application.user.dto;

import ac.dnd.dodal.domain.user.UserFixture;
import ac.dnd.dodal.domain.user.model.User;

public class AuthSignUpFixture {

  public static User signUpUser() {
    return UserFixture.createUser("test2@test.example.com", "testUser2", "profileImageURLExample2", "deviceTokenExample2");
  }

}
