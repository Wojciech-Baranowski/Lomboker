package com.simmondobber.lomboker.lombokize.boilerplateCleaner.methodFactory.getterFactory;

import org.apache.commons.lang3.StringUtils;

public class GetterNameFactory {

    private final String fieldType;
    private final String fieldName;

    public GetterNameFactory(String fieldType, String fieldName) {
        this.fieldType = fieldType;
        this.fieldName = fieldName;
    }

    public String createGetterName() {
        if (this.fieldType.equals("boolean")) {
            return createBooleanGetterName();
        } else {
            return createStandardGetterName();
        }
    }

    private String createBooleanGetterName() {
        if (this.fieldName.startsWith("is")) {
            return createIsPrefixBooleanGetterName();
        } else {
            return createStandardBooleanGetterName();
        }
    }

    private String createStandardGetterName() {
        return "get" + StringUtils.capitalize(this.fieldName);
    }

    private String createStandardBooleanGetterName() {
        return "is" + StringUtils.capitalize(this.fieldName);
    }

    private String createIsPrefixBooleanGetterName() {
        return this.fieldName;
    }
}
