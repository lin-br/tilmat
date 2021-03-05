package com.tilmais.usecases.validators.exeptions;

public abstract class ValidationException extends Exception {
  ValidationException(String message) {
    super(message);
  }
}
