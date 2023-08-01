package com.simmondobber.lomboker.common;

import lombok.Getter;

@Getter
public enum ClassTypeKeywords {

    CLASS("class"),
    ENUM("enum"),
    INTERFACE("interface"),
    ANNOTATION("@interface"),
    RECORD("record");

    private final String keyword;

    ClassTypeKeywords(String keyword) {
        this.keyword = keyword;
    }
}
