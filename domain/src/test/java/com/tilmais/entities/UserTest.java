package com.tilmais.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

  private final Faker faker = new Faker();

  @Test
  @DisplayName("Should return false if is the user same status.")
  void shouldReturnFalseIfIsSameStatus() {
    final var status = faker.internet().avatar();
    final var user = User.builder().setStatusDescription(status).build();
    assertFalse(user.isNotYourStatus(status));
  }

  @Test
  @DisplayName("Should return false if is the user same name.")
  void shouldReturnFalseIfIsSameName() {
    final var name = faker.name().firstName();
    final var user = User.builder().setName(name).build();
    assertFalse(user.isNotYourName(name));
  }

  @Test
  @DisplayName("Should return true if is the same user object.")
  void shouldReturnTrueIfIsSameUser() {
    final var user = User.builder().setName(faker.name().firstName())
        .setEmailAddress(faker.internet().emailAddress())
        .setStatusDescription(faker.internet().avatar()).build();
    assertEquals(user, user);
  }

  @Test
  @DisplayName("Should return false if is not the same user class.")
  void shouldReturnFalseIfIsNotSameUser() {
    final var user = User.builder().setName(faker.name().firstName())
        .setEmailAddress(faker.internet().emailAddress())
        .setStatusDescription(faker.internet().avatar()).build();
    assertNotEquals(user, "");
  }

  @Test
  @DisplayName("Should return false if the user is null to equals method.")
  void shouldReturnFalseIfIsUserIsNullToEqualsMethod() {
    final var user = User.builder().setName(faker.name().firstName())
        .setEmailAddress(faker.internet().emailAddress())
        .setStatusDescription(faker.internet().avatar()).build();
    assertNotEquals(user, null);
  }
}