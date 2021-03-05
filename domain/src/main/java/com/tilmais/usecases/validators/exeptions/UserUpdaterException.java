package com.tilmais.usecases.validators.exeptions;

public class UserUpdaterException extends ValidationException {

  public static final String TEXT_USER_NAME_IS_NULL_OR_EMPTY = "Name cannot be null or empty to update.";
  public static final String TEXT_USER_NULL = "User cannot be null to update.";
  public static final String TEXT_USER_STATUS_IS_NULL_OR_EMPTY = "Status description cannot be null or empty to update.";
  public static final String TEXT_USER_NAME_IS_BIG = "User name is more than 60 characters.";
  public static final String TEXT_USER_STATUS_IS_BIG = "User status is more than 60 characters.";

  private UserUpdaterException(String message) {
    super(message);
  }

  public static UserUpdaterException ofUserNameIsNullOrEmpty() {
    return new UserUpdaterException(TEXT_USER_NAME_IS_NULL_OR_EMPTY);
  }

  public static UserUpdaterException ofUserNull() {
    return new UserUpdaterException(TEXT_USER_NULL);
  }

  public static ValidationException ofUserStatusNull() {
    return new UserUpdaterException(TEXT_USER_STATUS_IS_NULL_OR_EMPTY);
  }

  public static ValidationException ofUserNameIsBig() {
    return new UserUpdaterException(TEXT_USER_NAME_IS_BIG);
  }

  public static ValidationException ofUserStatusIsBig() {
    return new UserUpdaterException(TEXT_USER_STATUS_IS_BIG);
  }
}
