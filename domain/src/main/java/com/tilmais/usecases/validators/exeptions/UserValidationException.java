package com.tilmais.usecases.validators.exeptions;

import java.text.MessageFormat;

public class UserValidationException extends ValidationException {

  private UserValidationException(String message) {
    super(message);
  }

  public static UserValidationException ofInvalidEmail(String email) {
    return new UserValidationException(MessageFormat.format("Invalid email address: [{0}].", email));
  }

  public static UserValidationException ofNullOrEmptyEmail() {
    return new UserValidationException("The user email address is null or empty.");
  }

  public static UserValidationException ofNullOrEmptyName() {
    return new UserValidationException("The user name is null or empty.");
  }

  public static UserValidationException ofNullOrEmptyCellPhone() {
    return new UserValidationException("The user cellphone number is null or empty.");
  }

  public static UserValidationException ofNullOrEmptyStatusDescription() {
    return new UserValidationException("The user status description is null or empty.");
  }

  public static UserValidationException ofUserNameIsBig() {
    return new UserValidationException("The user name is more than 100 characters.");
  }

  public static UserValidationException ofCellPhoneIsSmall() {
    return new UserValidationException("The cellphone number is less than 11 characters.");
  }

  public static UserValidationException ofStatusDescriptionIsBig() {
    return new UserValidationException("The status description is more than 60 characters.");
  }
}
