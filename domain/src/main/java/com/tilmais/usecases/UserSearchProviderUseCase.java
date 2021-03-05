package com.tilmais.usecases;

import com.tilmais.entities.User;
import com.tilmais.repositories.UserRepository;
import com.tilmais.usecases.validators.business.SingleEntityBusinessValidator;
import com.tilmais.usecases.validators.exeptions.ValidationException;
import java.util.Optional;

public class UserSearchProviderUseCase {

  private final UserRepository userRepository;
  private final SingleEntityBusinessValidator<String> userSearchValidator;

  public UserSearchProviderUseCase(UserRepository userRepository,
      SingleEntityBusinessValidator<String> userSearchValidator) {
    this.userRepository = userRepository;
    this.userSearchValidator = userSearchValidator;
  }

  public Optional<User> findByName(String name) throws ValidationException {
    return this.userSearchValidator.check(name)
        .ifValid(this.userRepository::findByName)
        .orThrowsExceptionValidation();
  }

  public Optional<User> findByCellPhone(String cellPhone) throws ValidationException {
    return this.userSearchValidator.check(cellPhone)
        .ifValid(this.userRepository::findByCellPhone)
        .orThrowsExceptionValidation();
  }
}
