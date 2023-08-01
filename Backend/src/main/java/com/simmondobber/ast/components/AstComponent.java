package com.simmondobber.ast.components;

public interface AstComponent {

    String getFullSyntax();

    String getFrontSeparator();

    String getBackSeparator();

    void setFrontSeparator(String separator);

    void setBackSeparator(String separator);
}
