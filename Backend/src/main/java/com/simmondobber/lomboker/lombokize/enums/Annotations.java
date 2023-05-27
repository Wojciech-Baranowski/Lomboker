package com.simmondobber.lomboker.lombokize.enums;

import lombok.Getter;

@Getter
public enum Annotations {

    GETTER("@Getter"),
    SETTER("@Setter");

    private final String keyword;

    Annotations(String symbol) {
        this.keyword = symbol;
    }
}
