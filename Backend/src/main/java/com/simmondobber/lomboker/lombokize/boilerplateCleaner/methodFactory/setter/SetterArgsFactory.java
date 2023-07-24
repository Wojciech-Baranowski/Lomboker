package com.simmondobber.lomboker.lombokize.boilerplateCleaner.methodFactory.setter;

import com.simmondobber.lomboker.common.Keywords;
import org.apache.commons.lang3.StringUtils;

public class SetterArgsFactory {

    private final String fieldType;
    private final String fieldName;

    public SetterArgsFactory(String fieldType, String fieldName) {
        this.fieldType = fieldType;
        this.fieldName = fieldName;
    }

    public String createSetterArgs() {
        if (this.fieldType.equals("boolean") && this.fieldName.startsWith("is")) {
            return createIsPrefixBooleanSetterArgs();
        } else {
            return createStandardSetterArgs();
        }
    }

    private String createIsPrefixBooleanSetterArgs() {
        if (Keywords.contains(StringUtils.uncapitalize(this.fieldName.substring(2)))) {
            return createIsPrefixKeywordBooleanSetterArgs();
        } else {
            return createStandardIsPrefixKeywordBooleanSetterArgs();
        }
    }

    private String createIsPrefixKeywordBooleanSetterArgs() {
        if ("aeiou".contains(Character.toString(StringUtils.uncapitalize(this.fieldName.substring(2)).charAt(0)))) {
            return createIsPrefixKeywordVowelBooleanSetterArgs();
        } else {
            return createIsPrefixKeywordConsonantBooleanSetterArgs();
        }
    }

    private String createStandardSetterArgs() {
        return "(" + this.fieldType + " " + this.fieldName + ")";
    }

    private String createStandardIsPrefixKeywordBooleanSetterArgs() {
        return "(" + this.fieldType + " " + StringUtils.uncapitalize(this.fieldName.substring(2)) + ")";
    }

    private String createIsPrefixKeywordVowelBooleanSetterArgs() {
        return "(" + this.fieldType + " an" + this.fieldName.substring(2) + ")";
    }

    private String createIsPrefixKeywordConsonantBooleanSetterArgs() {
        return "(" + this.fieldType + " a" + this.fieldName.substring(2) + ")";
    }
}
