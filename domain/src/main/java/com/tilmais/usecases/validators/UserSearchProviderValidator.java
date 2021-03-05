package com.tilmais.usecases.validators;

import com.tilmais.usecases.utils.StringUtils;
import com.tilmais.usecases.validators.business.SingleEntityBusinessValidator;
import com.tilmais.usecases.validators.exeptions.SearchProviderException;
import com.tilmais.usecases.validators.response.SingleValidationResultImpl;

public class UserSearchProviderValidator implements SingleEntityBusinessValidator<String> {

  @Override
  public SingleValidationResultImpl<String> check(String text) {
    var result = SingleValidationResultImpl.ofEntity(text);
    if (StringUtils.isNullOrEmpty(text)) {
      return result.declareFalseValidation(SearchProviderException.ofTextIsNullOrEmpty());
    }
    return result.declareTrueValidation();
  }
}
