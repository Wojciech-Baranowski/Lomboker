package com.simmondobber.ast.components;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class SimpleAstComponent implements AstComponent {

    private String syntax;
    private String frontSeparator;
    private String backSeparator;

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

    public String getFullSyntax() {
        return this.syntax + this.backSeparator;
    }
}
