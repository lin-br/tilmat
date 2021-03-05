package com.tilmais.usecases.validators.exeptions;

public class MessageValidationException extends ValidationException {

  public static final String USER_MESSAGE_IS_NULL = "User in message cannot be null.";
  public static final String TEXT_MESSAGE_IS_NULL_OR_EMPTY = "Text in message cannot be null or empty.";
  public static final String CHAT_MESSAGE_IS_NULL = "Chat object in message cannot be null.";

  private MessageValidationException(String message) {
    super(message);
  }

  public static MessageValidationException ofUserMessageNull() {
    return new MessageValidationException(USER_MESSAGE_IS_NULL);
  }

  public static MessageValidationException ofTextMessageNull() {
    return new MessageValidationException(TEXT_MESSAGE_IS_NULL_OR_EMPTY);
  }

  public static MessageValidationException ofChatMessageNull() {
    return new MessageValidationException(CHAT_MESSAGE_IS_NULL);
  }
}
