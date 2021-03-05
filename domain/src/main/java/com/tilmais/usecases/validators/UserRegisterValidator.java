package com.tilmais.usecases.validators;

import com.tilmais.entities.User;
import com.tilmais.usecases.utils.StringUtils;
import com.tilmais.usecases.validators.business.SingleEntityBusinessValidator;
import com.tilmais.usecases.validators.exeptions.UserValidationException;
import com.tilmais.usecases.validators.response.SingleValidationResultImpl;

public class UserRegisterValidator implements SingleEntityBusinessValidator<User> {

  @Override
  public SingleValidationResultImpl<User> check(User user) {
    var result = SingleValidationResultImpl.ofEntity(user);

    if (StringUtils.isNullOrEmpty(user.getName())) {
      return result
          .declareFalseValidation(UserValidationException.ofNullOrEmptyName());
    } else {
      if (StringUtils.isMoreThan(user.getName(), 100)) {
        return result
            .declareFalseValidation(UserValidationException.ofUserNameIsBig());
      }
    }

    if (StringUtils.isNullOrEmpty(user.getCellPhone())) {
      return result
          .declareFalseValidation(UserValidationException.ofNullOrEmptyCellPhone());
    } else {
      if (StringUtils.isLessThan(user.getCellPhone(), 11)) {
        return result
            .declareFalseValidation(UserValidationException.ofCellPhoneIsSmall());
      }
    }

    if (StringUtils.isNullOrEmpty(user.getStatusDescription())) {
      return result
          .declareFalseValidation(UserValidationException.ofNullOrEmptyStatusDescription());
    } else {
      if (StringUtils.isMoreThan(user.getStatusDescription(), 60)) {
        return result.declareFalseValidation(UserValidationException.ofStatusDescriptionIsBig());
      }
    }

    if (StringUtils.isNullOrEmpty(user.getEmailAddress())) {
      return result.declareFalseValidation(UserValidationException.ofNullOrEmptyEmail());
    }

    if (StringUtils.isInvalidEmail(user.getEmailAddress())) {
      return result
          .declareFalseValidation(UserValidationException.ofInvalidEmail(user.getEmailAddress()));
    }

    return result.declareTrueValidation();
  }
}
