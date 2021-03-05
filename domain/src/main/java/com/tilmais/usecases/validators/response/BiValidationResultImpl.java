package com.tilmais.usecases.validators.response;

import com.tilmais.usecases.validators.exeptions.ResultValidationException;
import com.tilmais.usecases.validators.exeptions.ValidationException;
import java.util.function.BiFunction;

public class BiValidationResultImpl<T, U> extends AbstractValidationResult<T> implements
    BiValidationResult<T, U> {

  private final U second;

  private BiValidationResultImpl(T first, U second) {
    super(first);
    this.second = second;
  }

  private BiValidationResultImpl(ValidationException validationException) {
    super(validationException);
    this.second = null;
  }

  public static <T, U> BiValidationResultImpl<T, U> ofEntity(T t, U u) {
    return new BiValidationResultImpl<>(t, u);
  }

  @Override
  public BiValidationResult<T, U> declareFalseValidation(ValidationException exception) {
    this.isValid = false;
    this.validationException = exception;
    return this;
  }

  @Override
  public BiValidationResult<T, U> declareTrueValidation() {
    this.isValid = true;
    return this;
  }

  @Override
  public <W> AbstractValidationResult<W> ifValid(
      BiFunction<? super T, ? super U, ? extends W> function) {
    if (function == null) {
      throw ResultValidationException.ofNullFunction();
    } else if (isValid) {
      return new BiValidationResultImpl<>(function.apply(this.entity, this.second), this.second);
    }
    return new BiValidationResultImpl<>(this.validationException);
  }
}
