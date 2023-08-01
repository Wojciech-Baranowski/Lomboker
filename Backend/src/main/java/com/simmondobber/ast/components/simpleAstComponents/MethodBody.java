package com.simmondobber.ast.components.simpleAstComponents;

import com.simmondobber.ast.components.SimpleAstComponent;

public class MethodBody extends SimpleAstComponent {

    public MethodBody(String syntax) {
        super(syntax);
    }

    public MethodBody(String syntax, String frontSeparator, String backSeparator) {
        super(syntax, frontSeparator, backSeparator);
    }
}
