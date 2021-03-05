package com.tilmais.usecases.validators.business;

import com.tilmais.usecases.validators.response.SingleValidationResultImpl;

public interface SingleEntityBusinessValidator<T> {
  SingleValidationResultImpl<T> check(T t);
}
