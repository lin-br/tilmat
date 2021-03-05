package com.tilmais.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.github.javafaker.Faker;
import com.tilmais.entities.User;
import com.tilmais.entities.User.BuilderUser;
import com.tilmais.repositories.UserRepository;
import com.tilmais.usecases.validators.UserRegisterValidator;
import com.tilmais.usecases.validators.exeptions.UserValidationException;
import com.tilmais.usecases.validators.exeptions.ValidationException;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserRegisterUseCaseTest {

  private final UserRegisterValidator validator = new UserRegisterValidator();
  private final Faker faker = new Faker();
  @Mock
  private UserRepository userRepository;
  private BuilderUser builderUser;

  @BeforeEach
  void setUp() {
    this.builderUser = User.builder()
        .setCellPhone(String.valueOf(faker.number().randomNumber(11, true)))
        .setEmailAddress(faker.internet().emailAddress())
        .setStatusDescription(faker.lorem().characters(15))
        .setName(faker.name().fullName());
  }

  @Test
  @DisplayName("Should throw an UserValidationException when the user name is null.")
  void shouldThrowUserValidationExceptionWhenTheUserNameIsNull() {
    var user = this.builderUser.setName(null).build();
    var useCase = new UserRegisterUseCase(this.userRepository, this.validator);

    var exception = assertThrows(UserValidationException.class,
        () -> useCase.registerUser(user));

    assertEquals("The user name is null or empty.", exception.getMessage());
    verifyNoInteractions(userRepository);
  }

  @Test
  @DisplayName("Should throw an UserValidationException when the user name is empty.")
  void shouldThrowUserValidationExceptionWhenTheUserNameIsEmpty() {
    var user = this.builderUser.setName("").build();
    var useCase = new UserRegisterUseCase(this.userRepository, this.validator);

    var exception = assertThrows(UserValidationException.class,
        () -> useCase.registerUser(user));

    assertEquals("The user name is null or empty.", exception.getMessage());
    verifyNoInteractions(userRepository);
  }

  @Test
  @DisplayName("Should throw an UserValidationException when the user name is blank.")
  void shouldThrowUserValidationExceptionWhenTheUserNameIsBlank() {
    var user = this.builderUser.setName("           ").build();
    var useCase = new UserRegisterUseCase(this.userRepository, this.validator);

    var exception = assertThrows(UserValidationException.class,
        () -> useCase.registerUser(user));

    assertEquals("The user name is null or empty.", exception.getMessage());
    verifyNoInteractions(userRepository);
  }

  @Test
  @DisplayName("Should throw an UserValidationException when the user cellphone is null.")
  void shouldThrowUserValidationExceptionWhenTheCellPhoneIsNull() {
    var user = this.builderUser.setCellPhone(null).build();
    var useCase = new UserRegisterUseCase(this.userRepository, this.validator);

    var exception = assertThrows(UserValidationException.class, () -> useCase.registerUser(user));

    assertEquals("The user cellphone number is null or empty.", exception.getMessage());
    verifyNoInteractions(userRepository);
  }

  @Test
  @DisplayName("Should throw an UserValidationException when the user cellphone is empty.")
  void shouldThrowUserValidationExceptionWhenTheCellPhoneIsEmpty() {
    var user = this.builderUser.setCellPhone("").build();
    var useCase = new UserRegisterUseCase(this.userRepository, this.validator);

    var exception = assertThrows(UserValidationException.class, () -> useCase.registerUser(user));

    assertEquals("The user cellphone number is null or empty.", exception.getMessage());
    verifyNoInteractions(userRepository);
  }

  @Test
  @DisplayName("Should throw an UserValidationException when the user cellphone is blank.")
  void shouldThrowUserValidationExceptionWhenTheCellPhoneIsBlank() {
    var user = this.builderUser.setCellPhone("        ").build();
    var useCase = new UserRegisterUseCase(this.userRepository, this.validator);

    var exception = assertThrows(UserValidationException.class, () -> useCase.registerUser(user));

    assertEquals("The user cellphone number is null or empty.", exception.getMessage());
    verifyNoInteractions(userRepository);
  }

  @Test
  @DisplayName("Should throw an UserValidationException when the user status description is null.")
  void shouldThrowUserValidationExceptionWhenTheStatusDescriptionIsNull() {
    var user = this.builderUser.setStatusDescription(null).build();
    var useCase = new UserRegisterUseCase(this.userRepository, this.validator);

    var exception = assertThrows(UserValidationException.class, () -> useCase.registerUser(user));

    assertEquals("The user status description is null or empty.", exception.getMessage());
    verifyNoInteractions(userRepository);
  }

  @Test
  @DisplayName("Should throw an UserValidationException when the user status description is empty.")
  void shouldThrowUserValidationExceptionWhenTheStatusDescriptionIsEmpty() {
    var user = this.builderUser.setStatusDescription("").build();
    var useCase = new UserRegisterUseCase(this.userRepository, this.validator);

    var exception = assertThrows(UserValidationException.class, () -> useCase.registerUser(user));

    assertEquals("The user status description is null or empty.", exception.getMessage());
    verifyNoInteractions(userRepository);
  }

  @Test
  @DisplayName("Should throw an UserValidationException when the user status description is blank.")
  void shouldThrowUserValidationExceptionWhenTheStatusDescriptionIsBlank() {
    var user = this.builderUser.setStatusDescription("         ").build();
    var useCase = new UserRegisterUseCase(this.userRepository, this.validator);

    var exception = assertThrows(UserValidationException.class, () -> useCase.registerUser(user));

    assertEquals("The user status description is null or empty.", exception.getMessage());
    verifyNoInteractions(userRepository);
  }

  @Test
  @DisplayName("Should throw an UserValidationException when the user email address is null.")
  void shouldThrowUserValidationExceptionWhenTheEmailAddressIsNull() {
    var user = this.builderUser.setEmailAddress(null).build();
    var useCase = new UserRegisterUseCase(this.userRepository, this.validator);

    var exception = assertThrows(UserValidationException.class, () -> useCase.registerUser(user));

    assertEquals("The user email address is null or empty.", exception.getMessage());
    verifyNoInteractions(userRepository);
  }

  @Test
  @DisplayName("Should throw an UserValidationException when the user email address is empty.")
  void shouldThrowUserValidationExceptionWhenTheEmailAddressIsEmpty() {
    var user = this.builderUser.setEmailAddress("").build();
    var useCase = new UserRegisterUseCase(this.userRepository, this.validator);

    var exception = assertThrows(UserValidationException.class, () -> useCase.registerUser(user));

    assertEquals("The user email address is null or empty.", exception.getMessage());
    verifyNoInteractions(userRepository);
  }

  @Test
  @DisplayName("Should throw an UserValidationException when the user email address is blank.")
  void shouldThrowUserValidationExceptionWhenTheEmailAddressIsBlank() {
    var user = this.builderUser.setEmailAddress("     ").build();
    var useCase = new UserRegisterUseCase(this.userRepository, this.validator);

    var exception = assertThrows(UserValidationException.class, () -> useCase.registerUser(user));

    assertEquals("The user email address is null or empty.", exception.getMessage());
    verifyNoInteractions(userRepository);
  }

  @Test
  @DisplayName("Should throw an UserValidationException when the user email address is invalid.")
  void shouldThrowUserValidationExceptionWhenTheEmailAddressIsInvalid() {
    var user = this.builderUser.setEmailAddress("email").build();
    var useCase = new UserRegisterUseCase(this.userRepository, this.validator);

    var exception = assertThrows(UserValidationException.class, () -> useCase.registerUser(user));

    assertEquals("Invalid email address: [email].", exception.getMessage());
    verifyNoInteractions(userRepository);
  }

  @Test
  @DisplayName("Should throw an UserValidationException when the user name is more than 100 characters.")
  void shouldThrowUserValidationExceptionWhenTheUserNameIsMoreThan100Characters() {
    var user = this.builderUser.setName(faker.lorem().characters(101)).build();
    var useCase = new UserRegisterUseCase(this.userRepository, this.validator);

    var exception = assertThrows(UserValidationException.class, () -> useCase.registerUser(user));

    assertEquals("The user name is more than 100 characters.", exception.getMessage());
    verifyNoInteractions(userRepository);
  }

  @Test
  @DisplayName("Should throw an UserValidationException when the cellphone number is less than 11 characters")
  void shouldThrowUserValidationExceptionWhenTheCellPhoneIsLessThanElevenCharacters() {
    var user = this.builderUser.setCellPhone(String.valueOf(faker.number().randomNumber(5, true)))
        .build();
    var useCase = new UserRegisterUseCase(this.userRepository, this.validator);

    var exception = assertThrows(UserValidationException.class, () -> useCase.registerUser(user));

    assertEquals("The cellphone number is less than 11 characters.", exception.getMessage());
    verifyNoInteractions(userRepository);
  }

  @Test
  @DisplayName("Should throw an UserValidationException when the status description is more than 60 characters")
  void shouldThrowUserValidationExceptionWhenTheStatusDescriptionIsMoreThan60Characters() {
    var user = this.builderUser.setStatusDescription(faker.lorem().characters(61)).build();
    var useCase = new UserRegisterUseCase(this.userRepository, this.validator);

    var exception = assertThrows(UserValidationException.class, () -> useCase.registerUser(user));

    assertEquals("The status description is more than 60 characters.", exception.getMessage());
    verifyNoInteractions(userRepository);
  }

  @Test
  @DisplayName("Should register an user when it is valid.")
  void shouldRegisterUserWhenItIsValid() throws ValidationException {
    var uuid = UUID.fromString("b3f00dd7-080c-4bb6-ba9c-a2561b884288");
    when(this.userRepository.registerUser(anyString(), anyString(), anyString()))
        .thenReturn(Optional.of(uuid));
    var useCase = new UserRegisterUseCase(this.userRepository, this.validator);

    var result = useCase.registerUser(builderUser.build());

    assertTrue(result.isPresent());
    assertEquals(uuid, result.get());
    verify(this.userRepository, only()).registerUser(anyString(), anyString(), anyString());
  }
}