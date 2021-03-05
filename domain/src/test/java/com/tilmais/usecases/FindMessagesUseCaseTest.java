package com.tilmais.usecases;

import static com.tilmais.usecases.validators.exeptions.ChatValidationException.IN_CHAT_SOME_USER_IS_NULL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.tilmais.entities.Chat;
import com.tilmais.entities.Chat.BuilderChat;
import com.tilmais.entities.Message;
import com.tilmais.entities.User;
import com.tilmais.repositories.MessageRepository;
import com.tilmais.usecases.validators.FindMessagesValidator;
import com.tilmais.usecases.validators.exeptions.ValidationException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FindMessagesUseCaseTest {

  private final FindMessagesValidator findMessageValidator = new FindMessagesValidator();

  @Mock
  private MessageRepository repository;
  private BuilderChat builderChat;

  @BeforeEach
  void setUp() {
    this.builderChat = Chat.builder()
        .fromUser(User.builder().build())
        .toUser(User.builder().build());
  }

  @Test
  @DisplayName("Should find all messages from the chat.")
  void shouldFindAllMessagesByChat() throws ValidationException {
    var chat = builderChat.build();
    when(this.repository.findMessagesByChat(any(Chat.class))).thenReturn(List.of(Message.builder()
        .build()));

    var useCase = new FindMessagesUseCase(this.repository, this.findMessageValidator);

    List<Message> allMessages = useCase.findAllMessages(chat);

    verify(this.repository, times(1)).findMessagesByChat(eq(chat));
    assertFalse(allMessages.isEmpty());
  }

  @Test
  @DisplayName("Should throw an exception when the user to send messages in chat is null.")
  void shouldThrowAnExceptionWhenTheUserToSendMessagesIsNull() {
    var chat = builderChat.toUser(null).build();
    var useCase = new FindMessagesUseCase(this.repository, this.findMessageValidator);

    var exception = assertThrows(ValidationException.class, () -> useCase.findAllMessages(chat));

    verifyNoInteractions(this.repository);
    assertEquals(IN_CHAT_SOME_USER_IS_NULL, exception.getMessage());
  }

  @Test
  @DisplayName("Should throw an exception when the from user is null.")
  void shouldThrowAnExceptionWhenTheFromUserIsNull() {
    var chat = builderChat.fromUser(null).build();
    var useCase = new FindMessagesUseCase(this.repository, this.findMessageValidator);

    var exception = assertThrows(ValidationException.class, () -> useCase.findAllMessages(chat));

    verifyNoInteractions(this.repository);
    assertEquals(IN_CHAT_SOME_USER_IS_NULL, exception.getMessage());
  }
}