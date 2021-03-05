package com.tilmais.usecases;

import com.tilmais.entities.User;
import com.tilmais.repositories.UserRepository;
import com.tilmais.usecases.validators.business.SingleEntityBusinessValidator;
import com.tilmais.usecases.validators.exeptions.ValidationException;
import java.util.Optional;
import java.util.UUID;

public class UserRegisterUseCase {

  private final UserRepository userRepository;
  private final SingleEntityBusinessValidator<User> registerUserValidator;

  public UserRegisterUseCase(UserRepository userRepository,
      SingleEntityBusinessValidator<User> registerUserValidator) {
    this.userRepository = userRepository;
    this.registerUserValidator = registerUserValidator;
  }

  public Optional<UUID> registerUser(User user) throws ValidationException {
    return registerUserValidator.check(user)
        .ifValid(us -> this.userRepository
            .registerUser(user.getCellPhone(), user.getName(), user.getEmailAddress()))
        .orThrowsExceptionValidation();
  }
}
