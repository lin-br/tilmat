package com.tilmais.usecases.validators.response;

import java.util.function.Consumer;

public interface VoidValidationResult<T> extends ValidationResult<T> {
  void ifValid(Consumer<? super T> function);
}
