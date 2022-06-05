package com.systech.notificationsystem.model.enums;

public enum MessagingUserType {
  MEMBER("Member"),
  PENSIONER("Pensioner"),
  UNITIZATION("Unitization Notification"),
  PROSPECTIVE_ANNUTANT("Prospective Annutant"),
  APPLICATION_USER("Application user"),
  OTHER("Other"),
  FUND_MANAGERS("Fund Manager"),
  SPONSOR("Sponsor"),
  SCHEME("Scheme"),
  CUSTODIAN("Custodian"),
  WORKFLOW("Workflow"),
  CLAIM("Claim");

  private String name;

  MessagingUserType(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
