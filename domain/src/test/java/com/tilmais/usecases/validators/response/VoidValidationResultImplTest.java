package com.tilmais.usecases.validators.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.javafaker.Faker;
import com.tilmais.usecases.validators.exeptions.ResultValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VoidValidationResultImplTest {

  private final Faker faker = new Faker();

  @Test
  @DisplayName("Should throw an exception when the parameter of 'ifValid' method is null")
  void shouldThrowAnExceptionWhenParameterIsNull() {
    var result = VoidValidationResultImpl.ofEntity(this.faker.name().fullName());

    var exception = assertThrows(ResultValidationException.class, () -> result.ifValid(null));

    assertEquals("Function of the validation result cannot be null.", exception.getMessage());
  }
}