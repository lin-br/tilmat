package com.tilmais.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.github.javafaker.Faker;
import com.tilmais.entities.User;
import com.tilmais.entities.User.BuilderUser;
import com.tilmais.repositories.UserRepository;
import com.tilmais.usecases.validators.UserSearchProviderValidator;
import com.tilmais.usecases.validators.exeptions.SearchProviderException;
import com.tilmais.usecases.validators.exeptions.ValidationException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserSearchProviderUseCaseTest {

  private final UserSearchProviderValidator validator = new UserSearchProviderValidator();
  private final Faker faker = new Faker();
  private BuilderUser builderUser;
  @Mock
  private UserRepository userRepository;

  @BeforeEach
  void setUp() {
    this.builderUser = User.builder().setName(faker.name().fullName());
  }

  @Test
  @DisplayName("Should find the user when trying search by name.")
  void shouldFindTheUserByName() throws ValidationException {
    when(this.userRepository.findByName(anyString()))
        .thenReturn(Optional.of(this.builderUser.build()));
    var usecase = new UserSearchProviderUseCase(this.userRepository, validator);

    var result = usecase.findByName("a name");

    assertTrue(result.isPresent());
    assertNotNull(result.get().getName());
    verify(this.userRepository, only()).findByName(eq("a name"));
  }

  @Test
  @DisplayName("Should find the user when trying search by cellphone number.")
  void shouldFindTheUserByCellPhone() throws ValidationException {
    when(this.userRepository.findByCellPhone(anyString()))
        .thenReturn(Optional.of(builderUser.build()));
    var usecase = new UserSearchProviderUseCase(this.userRepository, validator);

    var result = usecase.findByCellPhone("16900000009");

    assertTrue(result.isPresent());
    assertNotNull(result.get().getName());
    verify(this.userRepository, only()).findByCellPhone(eq("16900000009"));
  }

  @Test
  @DisplayName("Should throw an exception when the name to find is null.")
  void shouldThrowAnExceptionWhenTheNameToFindIsNull() {
    var usecase = new UserSearchProviderUseCase(this.userRepository, validator);

    var exception = assertThrows(ValidationException.class, () -> usecase.findByName(null));

    assertEquals(SearchProviderException.TEXT_NULL_OR_EMPTY_TO_SEARCH_ERROR,
        exception.getMessage());
    verifyNoInteractions(this.userRepository);
  }

  @Test
  @DisplayName("Should throw an exception when the name to find is empty.")
  void shouldThrowAnExceptionWhenTheNameToFindIsEmpty() {
    var usecase = new UserSearchProviderUseCase(this.userRepository, validator);

    var exception = assertThrows(ValidationException.class, () -> usecase.findByName(""));

    assertEquals(SearchProviderException.TEXT_NULL_OR_EMPTY_TO_SEARCH_ERROR,
        exception.getMessage());
    verifyNoInteractions(this.userRepository);
  }

  @Test
  @DisplayName("Should throw an exception when the name to find is blank.")
  void shouldThrowAnExceptionWhenTheNameToFindIsBlank() {
    var usecase = new UserSearchProviderUseCase(this.userRepository, validator);

    var exception = assertThrows(ValidationException.class, () -> usecase.findByName("    "));

    assertEquals(SearchProviderException.TEXT_NULL_OR_EMPTY_TO_SEARCH_ERROR,
        exception.getMessage());
    verifyNoInteractions(this.userRepository);
  }

  @Test
  @DisplayName("Should throw an exception when the cellphone to find is null.")
  void shouldThrowAnExceptionWhenTheCellPhoneToFindIsNull() {
    var usecase = new UserSearchProviderUseCase(this.userRepository, validator);

    var exception = assertThrows(ValidationException.class, () -> usecase.findByCellPhone(null));

    assertEquals(SearchProviderException.TEXT_NULL_OR_EMPTY_TO_SEARCH_ERROR,
        exception.getMessage());
    verifyNoInteractions(this.userRepository);
  }

  @Test
  @DisplayName("Should throw an exception when the cellphone to find is empty.")
  void shouldThrowAnExceptionWhenTheCellPhoneToFindIsEmpty() {
    var usecase = new UserSearchProviderUseCase(this.userRepository, validator);

    var exception = assertThrows(ValidationException.class, () -> usecase.findByCellPhone(""));

    assertEquals(SearchProviderException.TEXT_NULL_OR_EMPTY_TO_SEARCH_ERROR,
        exception.getMessage());
    verifyNoInteractions(this.userRepository);
  }

  @Test
  @DisplayName("Should throw an exception when the cellphone to find is blank.")
  void shouldThrowAnExceptionWhenTheCellPhoneToFindIsBlank() {
    var usecase = new UserSearchProviderUseCase(this.userRepository, validator);

    var exception = assertThrows(ValidationException.class, () -> usecase.findByCellPhone("   "));

    assertEquals(SearchProviderException.TEXT_NULL_OR_EMPTY_TO_SEARCH_ERROR,
        exception.getMessage());
    verifyNoInteractions(this.userRepository);
  }
}