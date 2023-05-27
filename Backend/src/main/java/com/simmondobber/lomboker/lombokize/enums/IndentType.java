package com.simmondobber.lomboker.lombokize.enums;

import lombok.Getter;

@Getter
public enum IndentType {

    SPACE("    "),
    TABULATOR("\t");

    private final String indent;

    IndentType(String symbol) {
        this.indent = symbol;
    }
}
