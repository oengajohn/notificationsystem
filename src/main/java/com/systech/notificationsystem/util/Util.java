package com.systech.notificationsystem.util;

public class Util {
    public static String sanitizePhoneNumber(String phoneNumber, String countryCode) {

        final String plus = "+";
        final String zero = "0";

        if (phoneNumber.startsWith(plus)) {
            phoneNumber = (phoneNumber.replace(plus, ""));
        }
        else if (phoneNumber.startsWith(zero)) {
            phoneNumber = countryCode + phoneNumber.substring(1);
        }
        return phoneNumber;
    }
}
