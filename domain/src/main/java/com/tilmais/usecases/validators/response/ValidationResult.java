package com.tilmais.usecases.validators.response;

import com.tilmais.usecases.validators.exeptions.ValidationException;

interface ValidationResult<T> {
  ValidationResult<T> declareFalseValidation(ValidationException exception);

  ValidationResult<T> declareTrueValidation();

  T orThrowsExceptionValidation() throws ValidationException;
}
