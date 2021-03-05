package com.tilmais.usecases.utils;

import java.util.regex.Pattern;

public final class StringUtils {

  public static boolean isNullOrEmpty(final String value) {
    return value == null || value.isEmpty() || value.isBlank();
  }

  public static boolean isInvalidEmail(String emailAddress) {
    return !Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$").matcher(emailAddress)
        .matches();
  }

  public static boolean isMoreThan(String value, int size) {
    return value.length() > size;
  }

  public static boolean isLessThan(String value, int size) {
    return value.length() < size;
  }
}
