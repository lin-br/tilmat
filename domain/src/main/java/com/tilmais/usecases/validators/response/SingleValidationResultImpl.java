package com.tilmais.usecases.validators.response;

import com.tilmais.usecases.validators.exeptions.ResultValidationException;
import com.tilmais.usecases.validators.exeptions.ValidationException;
import java.util.function.Function;

public class SingleValidationResultImpl<T> extends AbstractValidationResult<T> implements
    SingleValidationResult<T> {

  private SingleValidationResultImpl(T entity) {
    super(entity);
  }

  private SingleValidationResultImpl(ValidationException validationException) {
    super(validationException);
  }

  public static <T> SingleValidationResultImpl<T> ofEntity(T t) {
    return new SingleValidationResultImpl<>(t);
  }

  @Override
  public SingleValidationResultImpl<T> declareFalseValidation(ValidationException exception) {
    this.isValid = false;
    this.validationException = exception;
    return this;
  }

  @Override
  public SingleValidationResultImpl<T> declareTrueValidation() {
    this.isValid = true;
    return this;
  }

  @Override
  public <W> AbstractValidationResult<W> ifValid(Function<? super T, ? extends W> function) {
    if (function == null) {
      throw ResultValidationException.ofNullFunction();
    } else if (isValid) {
      return new SingleValidationResultImpl<>(function.apply(this.entity));
    }
    return new SingleValidationResultImpl<>(this.validationException);
  }
}
