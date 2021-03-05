package com.tilmais.usecases.validators.response;

import java.util.function.BiFunction;

public interface BiValidationResult<T, U> extends ValidationResult<T> {
  <W> AbstractValidationResult<W> ifValid(BiFunction<? super T, ? super U, ? extends W> function);
}
