package com.tilmais.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.github.javafaker.Faker;
import com.tilmais.entities.Chat;
import com.tilmais.entities.Message;
import com.tilmais.entities.Message.BuilderMessage;
import com.tilmais.entities.User;
import com.tilmais.repositories.MessageRepository;
import com.tilmais.usecases.validators.UserSendMessageValidator;
import com.tilmais.usecases.validators.exeptions.MessageValidationException;
import com.tilmais.usecases.validators.exeptions.ValidationException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserSendMessageUseCaseTest {

  private final UserSendMessageValidator validator = new UserSendMessageValidator();
  private final Faker faker = new Faker();
  @Mock
  private MessageRepository messageRepository;
  private BuilderMessage builder;

  @BeforeEach
  void setUp() {
    this.builder = Message.builder()
        .fromUser(User.builder().build())
        .fromChat(Chat.builder().build())
        .withText(faker.lorem().characters(100));
  }

  @Test
  @DisplayName("Should send message to user if everything is ok.")
  void shouldSendMessageToUser() throws ValidationException {
    Message message = this.builder.withText("test").build();
    when(this.messageRepository.sendMessage(any(Message.class)))
        .thenReturn(Optional.of(message));
    var useCase = new UserSendMessageUseCase(this.messageRepository, this.validator);

    Optional<Message> optional = useCase.sendMessageToUserInChat(message);

    assertTrue(optional.isPresent());
    assertNotNull(optional.get().getText());
    verify(this.messageRepository, only()).sendMessage(eq(message));
  }

  @Test
  @DisplayName("Should throw an exception when the user of message is null.")
  void shouldThrowAnExceptionWhenUserOfMessageIsNull() {
    Message message = this.builder.fromUser(null).build();
    var useCase = new UserSendMessageUseCase(this.messageRepository, this.validator);

    var exception = assertThrows(ValidationException.class,
        () -> useCase.sendMessageToUserInChat(message));

    assertEquals(MessageValidationException.USER_MESSAGE_IS_NULL, exception.getMessage());
    verifyNoInteractions(this.messageRepository);
  }

  @Test
  @DisplayName("Should throw an exception when the text of message is null.")
  void shouldThrowAnExceptionWhenTextIsNull() {
    Message message = this.builder.withText(null).build();
    var useCase = new UserSendMessageUseCase(this.messageRepository, this.validator);

    var exception = assertThrows(ValidationException.class,
        () -> useCase.sendMessageToUserInChat(message));

    assertEquals(MessageValidationException.TEXT_MESSAGE_IS_NULL_OR_EMPTY, exception.getMessage());
    verifyNoInteractions(this.messageRepository);
  }

  @Test
  @DisplayName("Should throw an exception when the text of message is empty.")
  void shouldThrowAnExceptionWhenTextIsEmpty() {
    Message message = this.builder.withText("").build();
    var useCase = new UserSendMessageUseCase(this.messageRepository, this.validator);

    var exception = assertThrows(ValidationException.class,
        () -> useCase.sendMessageToUserInChat(message));

    assertEquals(MessageValidationException.TEXT_MESSAGE_IS_NULL_OR_EMPTY, exception.getMessage());
    verifyNoInteractions(this.messageRepository);
  }

  @Test
  @DisplayName("Should throw an exception when the chat of message is null.")
  void shouldThrowAnExceptionWhenChatIsNull() {
    Message message = this.builder.fromChat(null).build();
    var useCase = new UserSendMessageUseCase(this.messageRepository, this.validator);

    var exception = assertThrows(ValidationException.class,
        () -> useCase.sendMessageToUserInChat(message));

    assertEquals(MessageValidationException.CHAT_MESSAGE_IS_NULL, exception.getMessage());
    verifyNoInteractions(this.messageRepository);
  }
}