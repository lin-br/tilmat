package com.tilmais.usecases.validators;

import com.tilmais.entities.User;
import com.tilmais.usecases.validators.business.BiEntityBusinessValidator;
import com.tilmais.usecases.validators.exeptions.ChatValidationException;
import com.tilmais.usecases.validators.response.BiValidationResult;
import com.tilmais.usecases.validators.response.BiValidationResultImpl;

public class ChatInitiatorValidator implements BiEntityBusinessValidator<User, User> {

  @Override
  public BiValidationResult<User, User> check(User fromUser, User toUser) {
    var result = BiValidationResultImpl.ofEntity(fromUser, toUser);
    if (fromUser.equals(toUser)) {
      return result
          .declareFalseValidation(ChatValidationException.ofSameUsersCellphone(fromUser, toUser));
    }
    return result.declareTrueValidation();
  }
}
