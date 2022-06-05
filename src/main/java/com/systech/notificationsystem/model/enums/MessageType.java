package com.systech.notificationsystem.model.enums;

public enum MessageType {
  INBOX("Inbox"),
  SENTBOX("Sentbox"),
  DRAFT("Draft");

  private String name;

  MessageType(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
