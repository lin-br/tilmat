package com.tilmais.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.tilmais.entities.User;
import com.tilmais.repositories.ChatRepository;
import com.tilmais.usecases.validators.ChatInitiatorValidator;
import com.tilmais.usecases.validators.exeptions.ChatValidationException;
import com.tilmais.usecases.validators.exeptions.ValidationException;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ChatInitiatorUseCaseTest {

  @Mock
  private ChatRepository chatRepository;

  private final ChatInitiatorValidator validator = new ChatInitiatorValidator();

  @Test
  @DisplayName("Should throws an ChatValidationException when trying init a chat to same cellphone number.")
  void shouldThrowChatValidationException() {
    var cellPhone = "16985855858";
    var useCase = new ChatInitiatorUseCase(this.chatRepository, this.validator);

    assertThrows(ChatValidationException.class,
        () -> useCase.initChat(User.builder().setCellPhone(cellPhone).build(),
            User.builder().setCellPhone(cellPhone).build()));
    verifyNoInteractions(this.chatRepository);
  }

  @Test
  @DisplayName("Should init a chat to different cellphone users.")
  void shouldInitChatToDifferentUsers() throws ValidationException {
    when(this.chatRepository.initChat(any(User.class), any(User.class)))
        .thenReturn(Optional.of(Long.MIN_VALUE));
    var cellPhoneOne = "16985853030";
    var cellPhoneTwo = "16985855858";
    var useCase = new ChatInitiatorUseCase(this.chatRepository, this.validator);

    Optional<Long> optional = useCase.initChat(User.builder().setCellPhone(cellPhoneOne).build(),
        User.builder().setCellPhone(cellPhoneTwo).build());
    assertTrue(optional.isPresent());
    assertEquals(Long.MIN_VALUE, optional.get());
    verify(this.chatRepository, only()).initChat(any(User.class), any(User.class));
  }
}