package com.tilmais.usecases.validators.exeptions;

public class SearchProviderException extends ValidationException {

  public static final String TEXT_NULL_OR_EMPTY_TO_SEARCH_ERROR = "Impossible find an user because the text to search is null or empty.";

  private SearchProviderException(String message) {
    super(message);
  }

  public static SearchProviderException ofTextIsNullOrEmpty() {
    return new SearchProviderException(TEXT_NULL_OR_EMPTY_TO_SEARCH_ERROR);
  }
}
