package com.tilmais.usecases.validators.business;

import com.tilmais.usecases.validators.response.BiValidationResult;

public interface BiEntityBusinessValidator<T, U> {
  BiValidationResult<T, U> check(T t, U u);
}
