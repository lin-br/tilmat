package com.tilmais.usecases;

import com.tilmais.entities.User;
import com.tilmais.repositories.ChatRepository;
import com.tilmais.usecases.validators.business.BiEntityBusinessValidator;
import com.tilmais.usecases.validators.exeptions.ValidationException;
import java.util.Optional;

public class ChatInitiatorUseCase {

  private final ChatRepository chatRepository;
  private final BiEntityBusinessValidator<User, User> chatInitiatorValidator;

  public ChatInitiatorUseCase(final ChatRepository chatRepository,
      final BiEntityBusinessValidator<User, User> chatInitiatorValidator) {
    this.chatRepository = chatRepository;
    this.chatInitiatorValidator = chatInitiatorValidator;
  }

  public Optional<Long> initChat(User fromUser, User toUser) throws ValidationException {
    return this.chatInitiatorValidator.check(fromUser, toUser)
        .ifValid(this.chatRepository::initChat)
        .orThrowsExceptionValidation();
  }
}
