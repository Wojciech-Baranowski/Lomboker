package com.simmondobber.ast.components.simpleAstComponents;

import com.simmondobber.ast.components.SimpleAstComponent;

public class Name extends SimpleAstComponent {

    public Name(String syntax) {
        super(syntax);
    }

    public Name(String syntax, String frontSeparator, String backSeparator) {
        super(syntax, frontSeparator, backSeparator);
    }
}
