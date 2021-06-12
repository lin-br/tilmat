package com.tilmais.usecases.validators.response;

import com.tilmais.usecases.validators.exeptions.ValidationException;

public abstract class AbstractValidationResult<T> {

  protected T entity;
  protected boolean isValid;
  protected ValidationException validationException;

  protected AbstractValidationResult(T entity) {
    this.entity = entity;
    this.isValid = true;
  }

  protected AbstractValidationResult(ValidationException validationException) {
    this.entity = null;
    this.isValid = false;
    this.validationException = validationException;
  }

  public T orThrowsExceptionValidation() throws ValidationException {
    if (isValid) {
      return entity;
    } else {
      throw this.validationException;
    }
  }
}
