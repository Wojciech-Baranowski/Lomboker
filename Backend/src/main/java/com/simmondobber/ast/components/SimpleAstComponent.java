package com.simmondobber.ast.components;

public abstract class SimpleAstComponent implements AstComponent {

    private final String syntax;
    private final String separator;

    public SimpleAstComponent(String syntax) {
        this.syntax = syntax;
        this.separator = "";
    }

    public SimpleAstComponent(String syntax, String separator) {
        this.syntax = syntax;
        this.separator = separator;
    }

    @Override
    public String getSyntax() {
        return this.syntax + this.separator;
    }
}
