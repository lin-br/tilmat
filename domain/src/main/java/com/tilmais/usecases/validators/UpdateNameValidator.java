package com.tilmais.usecases.validators;

import com.tilmais.usecases.utils.StringUtils;
import com.tilmais.usecases.validators.business.VoidEntityBusinessValidator;
import com.tilmais.usecases.validators.exeptions.UserUpdaterException;
import com.tilmais.usecases.validators.response.VoidValidationResultImpl;

public class UpdateNameValidator implements VoidEntityBusinessValidator<String> {

  @Override
  public VoidValidationResultImpl<String> check(String name) {
    var result = VoidValidationResultImpl.ofEntity(name);

    if (StringUtils.isNullOrEmpty(name)) {
      return result.declareFalseValidation(UserUpdaterException.ofUserNameIsNullOrEmpty());
    }

    if (StringUtils.isMoreThan(name, 60)) {
      return result.declareFalseValidation(UserUpdaterException.ofUserNameIsBig());
    }

    return result.declareTrueValidation();
  }
}
