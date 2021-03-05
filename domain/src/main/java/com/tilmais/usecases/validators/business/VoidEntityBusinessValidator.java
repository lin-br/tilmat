package com.tilmais.usecases.validators.business;

import com.tilmais.usecases.validators.response.VoidValidationResultImpl;

public interface VoidEntityBusinessValidator<T> {
  VoidValidationResultImpl<T> check(T t);
}
