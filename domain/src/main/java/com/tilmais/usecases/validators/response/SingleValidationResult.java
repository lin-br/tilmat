package com.tilmais.usecases.validators.response;

import java.util.function.Function;

public interface SingleValidationResult<T> extends ValidationResult<T> {
  <W> AbstractValidationResult<W> ifValid(Function<? super T, ? extends W> function);
}
