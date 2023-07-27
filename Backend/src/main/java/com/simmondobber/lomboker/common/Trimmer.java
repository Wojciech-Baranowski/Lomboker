package com.simmondobber.lomboker.common;

public class Trimmer {

    public static String trim(String stringToTrim) {
        return stringToTrim.replaceAll("`.*?`", "").trim();
    }

    public static String compressSeparators(String stringToTrim) {
        return stringToTrim.replaceAll("`.*?`", " ").replaceAll("\\s+", " ");
    }

    public static String adjustIndents(String code) {
        int indexOfFirstNonWhiteCharacter = code.indexOf(code.trim());
        String indentToAdjust = code.substring(0, indexOfFirstNonWhiteCharacter);
        return code.replaceAll("\n" + indentToAdjust, "\n").substring(indexOfFirstNonWhiteCharacter);
    }
}
