package com.tilmais.usecases.validators.exeptions;

import com.tilmais.entities.User;
import java.text.MessageFormat;

public class ChatValidationException extends ValidationException {

  public static final String IN_CHAT_SOME_USER_IS_NULL = "Some user in this chat is null";

  private ChatValidationException(String message) {
    super(message);
  }

  public static ChatValidationException ofSameUsersCellphone(User fromUser, User toUser) {
    return new ChatValidationException(MessageFormat
        .format("Impossible init a chat to same cellphone numbers: {0} and {1}",
            fromUser.getCellPhone(), toUser.getCellPhone()));
  }

  public static ValidationException ofSomeUserIsNull() {
    return new ChatValidationException(IN_CHAT_SOME_USER_IS_NULL);
  }
}
