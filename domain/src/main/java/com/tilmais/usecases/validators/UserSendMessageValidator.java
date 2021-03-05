package com.tilmais.usecases.validators;

import com.tilmais.entities.Message;
import com.tilmais.usecases.utils.StringUtils;
import com.tilmais.usecases.validators.business.SingleEntityBusinessValidator;
import com.tilmais.usecases.validators.exeptions.MessageValidationException;
import com.tilmais.usecases.validators.response.SingleValidationResultImpl;
import java.util.Objects;

public class UserSendMessageValidator implements SingleEntityBusinessValidator<Message> {

  @Override
  public SingleValidationResultImpl<Message> check(Message message) {
    var result = SingleValidationResultImpl.ofEntity(message);
    if (Objects.isNull(message.getFromUser())) {
      return result.declareFalseValidation(MessageValidationException.ofUserMessageNull());
    }

    if (Objects.isNull(message.getChat())) {
      return result.declareFalseValidation(MessageValidationException.ofChatMessageNull());
    }

    if (StringUtils.isNullOrEmpty(message.getText())) {
      return result.declareFalseValidation(MessageValidationException.ofTextMessageNull());
    }

    return result.declareTrueValidation();
  }
}
