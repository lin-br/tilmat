package com.tilmais.usecases.validators;


import com.tilmais.usecases.utils.StringUtils;
import com.tilmais.usecases.validators.business.VoidEntityBusinessValidator;
import com.tilmais.usecases.validators.exeptions.UserUpdaterException;
import com.tilmais.usecases.validators.response.VoidValidationResultImpl;

public class UpdateStatusValidator implements VoidEntityBusinessValidator<String> {

  @Override
  public VoidValidationResultImpl<String> check(String status) {
    var result = VoidValidationResultImpl.ofEntity(status);

    if (StringUtils.isNullOrEmpty(status)) {
      return result.declareFalseValidation(UserUpdaterException.ofUserStatusNull());
    }

    if (StringUtils.isMoreThan(status, 60)) {
      return result.declareFalseValidation(UserUpdaterException.ofUserStatusIsBig());
    }

    return result.declareTrueValidation();
  }
}
