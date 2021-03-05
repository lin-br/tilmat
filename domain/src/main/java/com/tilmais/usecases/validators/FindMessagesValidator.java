package com.tilmais.usecases.validators;

import com.tilmais.entities.Chat;
import com.tilmais.usecases.validators.business.SingleEntityBusinessValidator;
import com.tilmais.usecases.validators.exeptions.ChatValidationException;
import com.tilmais.usecases.validators.response.SingleValidationResultImpl;
import java.util.Objects;

public class FindMessagesValidator implements SingleEntityBusinessValidator<Chat> {

  @Override
  public SingleValidationResultImpl<Chat> check(Chat chat) {
    var result = SingleValidationResultImpl.ofEntity(chat);

    if (Objects.isNull(chat.getFromUser()) || Objects.isNull(chat.getToUser())) {
      return result.declareFalseValidation(ChatValidationException.ofSomeUserIsNull());
    }

    return result.declareTrueValidation();
  }
}
