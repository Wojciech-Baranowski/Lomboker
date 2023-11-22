package com.simmondobber.lomboker.common;

import lombok.Getter;

@Getter
public enum ClassTypeKeywordData {

    CLASS("class"),
    ENUM("enum"),
    INTERFACE("interface"),
    ANNOTATION("@interface"),
    RECORD("record");

    private final String keyword;

    ClassTypeKeywordData(String keyword) {
        this.keyword = keyword;
    }
}
