package com.simmondobber.ast.components;

import lombok.Getter;

public abstract class SimpleAstComponent implements AstComponent {

    @Getter
    private final String syntax;

    public SimpleAstComponent(String syntax) {
        this.syntax = syntax;
    }
}
