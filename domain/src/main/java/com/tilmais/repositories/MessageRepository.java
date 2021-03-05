package com.tilmais.repositories;

import com.tilmais.entities.Chat;
import com.tilmais.entities.Message;
import java.util.List;
import java.util.Optional;

public interface MessageRepository {
  Optional<Message> sendMessage(Message message);
  List<Message> findMessagesByChat(Chat chat);
}
