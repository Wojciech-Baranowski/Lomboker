package com.simmondobber.ast.components;

import lombok.Getter;

public abstract class SimpleAstComponent implements AstComponent {

    private final String syntax;
    @Getter
    private final String frontSeparator;
    @Getter
    private final String backSeparator;

    public SimpleAstComponent(String syntax) {
        this.syntax = syntax;
        this.backSeparator = "";
        this.frontSeparator = "";
    }

    public SimpleAstComponent(String syntax, String frontSeparator, String backSeparator) {
        this.syntax = syntax;
        this.frontSeparator = frontSeparator;
        this.backSeparator = backSeparator;
    }

    @Override
    public String getSyntax() {
        return this.syntax + this.backSeparator;
    }
}
