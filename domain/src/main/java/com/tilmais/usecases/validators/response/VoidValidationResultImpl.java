package com.tilmais.usecases.validators.response;

import com.tilmais.usecases.validators.exeptions.ResultValidationException;
import com.tilmais.usecases.validators.exeptions.ValidationException;
import java.util.function.Consumer;

public class VoidValidationResultImpl<T> extends AbstractValidationResult<T> implements
    VoidValidationResult<T> {

  private VoidValidationResultImpl(T entity) {
    super(entity);
  }

  public static <T> VoidValidationResultImpl<T> ofEntity(T t) {
    return new VoidValidationResultImpl<>(t);
  }

  @Override
  public VoidValidationResultImpl<T> declareFalseValidation(ValidationException exception) {
    this.isValid = false;
    this.validationException = exception;
    return this;
  }

  @Override
  public VoidValidationResultImpl<T> declareTrueValidation() {
    this.isValid = true;
    return this;
  }

  @Override
  public void ifValid(Consumer<? super T> consumer) {
    if (consumer == null) {
      throw ResultValidationException.ofNullFunction();
    } else if (isValid) {
      consumer.accept(this.entity);
    }
  }

  public VoidValidationResultImpl<T> ifInvalidThrowException() throws ValidationException {
    if (this.isValid) {
      return this;
    }
    throw this.validationException;
  }
}
