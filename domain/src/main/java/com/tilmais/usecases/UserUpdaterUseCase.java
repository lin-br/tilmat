package com.tilmais.usecases;

import com.tilmais.entities.User;
import com.tilmais.repositories.UserRepository;
import com.tilmais.usecases.validators.business.VoidEntityBusinessValidator;
import com.tilmais.usecases.validators.exeptions.UserUpdaterException;
import com.tilmais.usecases.validators.exeptions.UserValidationException;
import com.tilmais.usecases.validators.exeptions.ValidationException;
import java.util.Objects;

public class UserUpdaterUseCase {

  private final UserRepository userRepository;
  private final VoidEntityBusinessValidator<String> updateStatusValidator;
  private final VoidEntityBusinessValidator<String> updateNameValidator;

  public UserUpdaterUseCase(UserRepository userRepository,
      VoidEntityBusinessValidator<String> updateStatusValidator,
      VoidEntityBusinessValidator<String> updateNameValidator) {
    this.userRepository = userRepository;
    this.updateStatusValidator = updateStatusValidator;
    this.updateNameValidator = updateNameValidator;
  }

  public void updateUserStatusDescription(User user, String statusDescription)
      throws ValidationException {
    if (user == null) throw UserUpdaterException.ofUserNull();

    this.updateStatusValidator.check(statusDescription).ifInvalidThrowException()
        .ifValid(result -> {
          if (user.isNotYourStatus(result)) {
            this.userRepository.updateUserStatusDescription(user, result);
          }
        });
  }

  public void updateUserName(User user, String name) throws ValidationException {
    if (user == null) throw UserUpdaterException.ofUserNull();

    this.updateNameValidator.check(name).ifInvalidThrowException().ifValid(result -> {
      if (user.isNotYourName(result)) {
        this.userRepository.updateUserName(user, result);
      }
    });
  }
}
