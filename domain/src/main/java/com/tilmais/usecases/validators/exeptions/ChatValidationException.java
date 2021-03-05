package com.tilmais.usecases.validators.exeptions;

import com.tilmais.entities.User;
import java.text.MessageFormat;

public class ChatValidationException extends ValidationException {

  private ChatValidationException(String message) {
    super(message);
  }

  public static ChatValidationException ofSameUsersCellphone(User fromUser, User toUser) {
    return new ChatValidationException(MessageFormat
        .format("Impossible init a chat to same cellphone numbers: {0} and {1}",
            fromUser.getCellPhone(), toUser.getCellPhone()));
  }
}
