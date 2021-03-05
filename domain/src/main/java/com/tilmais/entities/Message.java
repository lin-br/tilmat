package com.tilmais.entities;

public class Message {

  private final Chat chat;
  private final User fromUser;
  private final String text;

  private Message(Chat chat, String text, User fromUser) {
    this.chat = chat;
    this.text = text;
    this.fromUser = fromUser;
  }

  public Chat getChat() {
    return chat;
  }

  public String getText() {
    return text;
  }

  public User getFromUser() {
    return fromUser;
  }

  public static class BuilderMessage {

    private String text;
    private User fromUser;
    private Chat chat;

    public BuilderMessage fromChat(final Chat chat) {
      this.chat = chat;
      return this;
    }

    public BuilderMessage fromUser(final User fromUser) {
      this.fromUser = fromUser;
      return this;
    }

    public BuilderMessage withText(final String text) {
      this.text = text;
      return this;
    }

    public Message build() {
      return new Message(this.chat, this.text, this.fromUser);
    }
  }

  public static BuilderMessage builder() {
    return new BuilderMessage();
  }
}
