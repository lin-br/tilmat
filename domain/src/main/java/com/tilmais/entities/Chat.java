package com.tilmais.entities;

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

    public void fromUser(final User user) {
      this.fromUser = user;
    }

    public void toUser(final User user) {
      this.toUser = user;
    }

    public BuilderChat withMessages(List<Message> messages) {
      this.messages = messages;
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
