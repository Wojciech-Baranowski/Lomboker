package com.simmondobber.lomboker.codeElement;

import lombok.Getter;

@Getter
public enum MethodType {
    GETTER("@Getter"),
    SETTER("@Setter");

    private final String annotation;

    MethodType(String annotation) {
        this.annotation = annotation;
    }
}
