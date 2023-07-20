package com.simmondobber.lomboker.common;

public class Trimmer {

    public static String trim(String stringToTrim) {
        return stringToTrim.replaceAll("`.*?`", "").trim();
    }
}
