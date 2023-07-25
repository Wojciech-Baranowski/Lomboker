package com.simmondobber.ast.components.simpleAstComponents;

import com.simmondobber.ast.components.SimpleAstComponent;

public class ArraySize extends SimpleAstComponent {

    public ArraySize(String syntax, String backSeparator) {
        super(syntax, "", backSeparator);
    }

    public ArraySize(String syntax, String frontSeparator, String backSeparator) {
        super(syntax, frontSeparator, backSeparator);
    }
}
