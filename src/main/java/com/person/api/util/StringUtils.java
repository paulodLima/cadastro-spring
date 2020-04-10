package com.person.api.util;

public class StringUtils {

    private StringUtils() {
    }

    public static String removeNonNumericCharacters(String source) {
        return source.replaceAll("\\D", "");
    }
}
