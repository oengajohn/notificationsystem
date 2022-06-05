package com.systech.notificationsystem.model.enums;

public enum SmsType {

    CONTRIBUTION("Contribution"),
    MEMBER_BALANCE("Member Balance"),
    MEMBER_BIRTHDAY("Member Birthday"),
    CLAIM_INITIALIZATION("Claim Initialization"),
    CLAIM_APPROVAL("Claim Approval"),
    PENSIONER_VERIFICATION("Pensioner Verification"),
    PASSWORD_RESET("Password Reset"),
    BENEFITS_LOG("Benefits Log"),
    OTHERS("Others");

    private String name;
    private SmsType(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}