package com.tilmais.usecases;

import com.tilmais.entities.Chat;
import com.tilmais.entities.Message;
import com.tilmais.repositories.MessageRepository;
import com.tilmais.usecases.validators.business.SingleEntityBusinessValidator;
import com.tilmais.usecases.validators.exeptions.ValidationException;
import java.util.List;

public class FindMessagesUseCase {

  private final MessageRepository messageRepository;
  private final SingleEntityBusinessValidator<Chat> findMessagesValidator;

  public FindMessagesUseCase(MessageRepository messageRepository,
      SingleEntityBusinessValidator<Chat> findMessagesValidator) {
    this.messageRepository = messageRepository;
    this.findMessagesValidator = findMessagesValidator;
  }

  public List<Message> findAllMessages(Chat chat) throws ValidationException {
    return this.findMessagesValidator.check(chat)
        .ifValid(this.messageRepository::findMessagesByChat).orThrowsExceptionValidation();
  }
}
