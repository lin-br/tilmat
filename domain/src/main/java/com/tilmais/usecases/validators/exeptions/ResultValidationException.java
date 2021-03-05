package com.tilmais.usecases.validators.exeptions;

public class ResultValidationException extends RuntimeException {
  private ResultValidationException(String message) {
    super(message);
  }

  public static ResultValidationException ofNullFunction() {
    return new ResultValidationException("Function of the validation result cannot be null.");
  }
}
