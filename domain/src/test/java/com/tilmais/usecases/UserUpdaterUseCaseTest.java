package com.tilmais.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import com.github.javafaker.Faker;
import com.tilmais.entities.User;
import com.tilmais.repositories.UserRepository;
import com.tilmais.usecases.validators.UpdateNameValidator;
import com.tilmais.usecases.validators.UpdateStatusValidator;
import com.tilmais.usecases.validators.exeptions.UserUpdaterException;
import com.tilmais.usecases.validators.exeptions.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserUpdaterUseCaseTest {

  private final UpdateStatusValidator updateStatusValidator = new UpdateStatusValidator();
  private final UpdateNameValidator updateNameValidator = new UpdateNameValidator();
  private final Faker faker = new Faker();
  @Mock
  private UserRepository userRepository;
  private User user;

  @BeforeEach
  void setUp() {
    this.user = User.builder()
        .setStatusDescription(faker.animal().name())
        .setName(faker.name().username())
        .setCellPhone(String.valueOf(faker.number().randomNumber(11, true)))
        .setEmailAddress(faker.internet().emailAddress())
        .build();
  }

  @Test
  @DisplayName("Should update user status if everything is ok.")
  void shouldUpdateUserStatus() throws ValidationException {
    var useCase = new UserUpdaterUseCase(this.userRepository, this.updateStatusValidator,
        this.updateNameValidator);

    useCase.updateUserStatusDescription(user, "Off, sorry :/");

    verify(this.userRepository, only()).updateUserStatusDescription(user, "Off, sorry :/");
  }

  @Test
  @DisplayName("Should update user name if everything is ok.")
  void shouldUpdateUserName() throws ValidationException {
    var useCase = new UserUpdaterUseCase(this.userRepository, this.updateStatusValidator,
        this.updateNameValidator);

    useCase.updateUserName(user, "Lin");

    verify(this.userRepository, only()).updateUserName(user, "Lin");
  }

  @Test
  @DisplayName("Should throw an exception when trying update user name and user is null.")
  void shouldThrowAnExceptionWhenUserIsNullToUpdateName() {
    var useCase = new UserUpdaterUseCase(this.userRepository, this.updateStatusValidator,
        this.updateNameValidator);

    var exception = assertThrows(ValidationException.class,
        () -> useCase.updateUserName(null, null));

    assertEquals(UserUpdaterException.TEXT_USER_NULL, exception.getMessage());
    verifyNoInteractions(this.userRepository);
  }

  @Test
  @DisplayName("Should throw an exception when trying update user status and user is null.")
  void shouldThrowAnExceptionWhenUserIsNullToUpdateState() {
    var useCase = new UserUpdaterUseCase(this.userRepository, this.updateStatusValidator,
        this.updateNameValidator);

    var exception = assertThrows(ValidationException.class,
        () -> useCase.updateUserStatusDescription(null, null));

    assertEquals(UserUpdaterException.TEXT_USER_NULL, exception.getMessage());
    verifyNoInteractions(this.userRepository);
  }

  @Test
  @DisplayName("Should throw an exception when trying updates user name and it is null.")
  void shouldThrowAnExceptionWhenNameIsNull() {
    var useCase = new UserUpdaterUseCase(this.userRepository, this.updateStatusValidator,
        this.updateNameValidator);

    var exception = assertThrows(ValidationException.class,
        () -> useCase.updateUserName(user, null));

    assertEquals(UserUpdaterException.TEXT_USER_NAME_IS_NULL_OR_EMPTY, exception.getMessage());
    verifyNoInteractions(this.userRepository);
  }

  @Test
  @DisplayName("Should throw an exception when trying updates user name and it is empty.")
  void shouldThrowAnExceptionWhenNameIsEmpty() {
    var useCase = new UserUpdaterUseCase(this.userRepository, this.updateStatusValidator,
        this.updateNameValidator);

    var exception = assertThrows(ValidationException.class,
        () -> useCase.updateUserName(user, ""));

    assertEquals(UserUpdaterException.TEXT_USER_NAME_IS_NULL_OR_EMPTY, exception.getMessage());
    verifyNoInteractions(this.userRepository);
  }

  @Test
  @DisplayName("Should throw an exception when trying updates user status and it is null.")
  void shouldThrowAnExceptionWhenStatusDescriptionIsNull() {
    var useCase = new UserUpdaterUseCase(this.userRepository, this.updateStatusValidator,
        this.updateNameValidator);

    var exception = assertThrows(ValidationException.class,
        () -> useCase.updateUserStatusDescription(user, null));

    assertEquals(UserUpdaterException.TEXT_USER_STATUS_IS_NULL_OR_EMPTY, exception.getMessage());
    verifyNoInteractions(this.userRepository);
  }

  @Test
  @DisplayName("Should throw an exception when trying updates user status and it is null.")
  void shouldThrowAnExceptionWhenStatusDescriptionIsEmpty() {
    var useCase = new UserUpdaterUseCase(this.userRepository, this.updateStatusValidator,
        this.updateNameValidator);

    var exception = assertThrows(ValidationException.class,
        () -> useCase.updateUserStatusDescription(user, ""));

    assertEquals(UserUpdaterException.TEXT_USER_STATUS_IS_NULL_OR_EMPTY, exception.getMessage());
    verifyNoInteractions(this.userRepository);
  }

  @Test
  @DisplayName("Should throw an exception when trying updates user name and it is more than 60 characters.")
  void shouldThrowAnExceptionWhenNameIsMoreThanSixtyCharacters() {
    var useCase = new UserUpdaterUseCase(this.userRepository, this.updateStatusValidator,
        this.updateNameValidator);

    var exception = assertThrows(ValidationException.class,
        () -> useCase.updateUserName(user, faker.lorem().characters(61)));

    assertEquals(UserUpdaterException.TEXT_USER_NAME_IS_BIG, exception.getMessage());
    verifyNoInteractions(this.userRepository);
  }

  @Test
  @DisplayName("Should throw an exception when trying updates user status and it is more than 60 characters.")
  void shouldThrowAnExceptionWhenStatusIsMoreThanSixtyCharacters() {
    var useCase = new UserUpdaterUseCase(this.userRepository, this.updateStatusValidator,
        this.updateNameValidator);

    var exception = assertThrows(ValidationException.class,
        () -> useCase.updateUserStatusDescription(user, faker.lorem().characters(61)));

    assertEquals(UserUpdaterException.TEXT_USER_STATUS_IS_BIG, exception.getMessage());
    verifyNoInteractions(this.userRepository);
  }

  @Test
  @DisplayName("Should do anything when the status to update is same from the user")
  void shouldDoAnythingWhenTheStatusToUpdateIsSameFromTheUser() throws ValidationException {
    var useCase = new UserUpdaterUseCase(this.userRepository, this.updateStatusValidator,
        this.updateNameValidator);

    useCase.updateUserStatusDescription(this.user, this.user.getStatusDescription());

    verify(this.userRepository, never()).updateUserStatusDescription(any(User.class), anyString());
  }

  @Test
  @DisplayName("Should do anything when the name to update is same from the user")
  void shouldDoAnythingWhenTheNameToUpdateIsSameFromTheUser() throws ValidationException {
    var useCase = new UserUpdaterUseCase(this.userRepository, this.updateStatusValidator,
        this.updateNameValidator);

    useCase.updateUserName(this.user, this.user.getName());

    verify(this.userRepository, never()).updateUserName(any(User.class), anyString());
  }
}