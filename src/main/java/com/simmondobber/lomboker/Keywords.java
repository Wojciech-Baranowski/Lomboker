package com.simmondobber.lomboker;

import lombok.Getter;

@Getter
public enum Keywords {

    BOOLEAN("boolean"),
    PUBLIC("public"),
    RETURN("return"),
    THIS("this"),
    VOID("void");

    private final String keyword;

    Keywords(String keyword) {
        this.keyword = keyword;
    }
}
