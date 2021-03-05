package com.tilmais.entities;

import java.util.ArrayList;
import java.util.List;

public class Chat {

  private final User fromUser;
  private final User toUser;
  private final List<Message> messages;

  public Chat(User fromUser, User toUser, List<Message> messages) {
    this.fromUser = fromUser;
    this.toUser = toUser;
    this.messages = messages;
  }

  public User getFromUser() {
    return fromUser;
  }

  public User getToUser() {
    return toUser;
  }

  public List<Message> getMessages() {
    return messages;
  }

  public static class BuilderChat {

    private List<Message> messages;
    private User fromUser;
    private User toUser;

    public BuilderChat fromUser(final User user) {
      this.fromUser = user;
      return this;
    }

    public BuilderChat toUser(final User user) {
      this.toUser = user;
      return this;
    }

    public BuilderChat withMessages(List<Message> messages) {
      this.messages = new ArrayList<>(messages);
      return this;
    }

    public Chat build() {
      return new Chat(this.fromUser, this.toUser, this.messages);
    }
  }

  public static BuilderChat builder() {
    return new BuilderChat();
  }
}
