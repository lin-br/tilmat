package com.tilmais.usecases;

import com.tilmais.entities.Message;
import com.tilmais.repositories.MessageRepository;
import com.tilmais.usecases.validators.business.SingleEntityBusinessValidator;
import com.tilmais.usecases.validators.exeptions.ValidationException;
import java.util.Optional;

public class UserSendMessageUseCase {

  private final MessageRepository messageRepository;
  private final SingleEntityBusinessValidator<Message> messageValidator;

  public UserSendMessageUseCase(MessageRepository messageRepository,
      SingleEntityBusinessValidator<Message> messageValidator) {
    this.messageRepository = messageRepository;
    this.messageValidator = messageValidator;
  }

  public Optional<Message> sendMessageToUserInChat(Message message) throws ValidationException {
    return this.messageValidator.check(message)
        .ifValid(response -> this.messageRepository.sendMessage(message))
        .orThrowsExceptionValidation();
  }
}
