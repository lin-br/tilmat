package com.tilmais.repositories;

import com.tilmais.entities.User;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

  Optional<UUID> registerUser(String cellPhone, String name, String emailAddress);

  Optional<User> findByName(String name);

  Optional<User> findByCellPhone(String cellPhone);

  void updateUserStatusDescription(User fromUser, String newDescription);

  void updateUserName(User fromUser, String newName);
}
