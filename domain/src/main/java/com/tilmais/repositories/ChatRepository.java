package com.tilmais.repositories;

import com.tilmais.entities.User;
import java.util.Optional;

public interface ChatRepository {
  Optional<Long> initChat(User fromUser, User toUser);
}
