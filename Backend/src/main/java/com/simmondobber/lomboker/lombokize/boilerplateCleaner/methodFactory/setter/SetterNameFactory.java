package com.simmondobber.lomboker.lombokize.boilerplateCleaner.methodFactory.setter;

import org.apache.commons.lang3.StringUtils;

public class SetterNameFactory {

    private final String fieldType;
    private final String fieldName;

    public SetterNameFactory(String fieldType, String fieldName) {
        this.fieldType = fieldType;
        this.fieldName = fieldName;
    }

    public String createSetterName() {
        if (this.fieldType.equals("boolean") && this.fieldName.startsWith("is")) {
            return createIsPrefixBooleanSetterName();
        } else {
            return createStandardSetterName();
        }
    }

    private String createStandardSetterName() {
        return "set" + StringUtils.capitalize(this.fieldName);
    }

    private String createIsPrefixBooleanSetterName() {
        return "set" + StringUtils.capitalize(this.fieldName.substring(2));
    }
}
